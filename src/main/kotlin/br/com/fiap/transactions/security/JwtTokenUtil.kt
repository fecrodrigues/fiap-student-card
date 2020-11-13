package br.com.fiap.transactions.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.util.*
import kotlin.collections.HashMap

@Component
class JwtTokenUtil {

    @Value("\${jwt.secret}") lateinit var secret: String
    @Value("\${jwt.expire}") var expire: Long = 0

    fun generateToken(login: String): String {
        var claims: Map<String, Object> = HashMap()

        var creationDate: Date = getFromLocalDateTime(LocalDateTime.now())
        var expirationDate: Date = getFromLocalDateTime(LocalDateTime.now().plusMinutes(expire))

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(login)
                .setIssuedAt(creationDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact()

    }

    fun getLoginFromToken(token: String): String {
        var claims: Claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token.replace("Bearer", ""))
                .body

        return claims.subject
    }

    fun getFromLocalDateTime(now: LocalDateTime): Date = Date.from(now.toInstant(OffsetDateTime.now().offset))


}