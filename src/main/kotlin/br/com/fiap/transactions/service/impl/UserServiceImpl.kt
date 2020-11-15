package br.com.fiap.transactions.service.impl

import br.com.fiap.transactions.dto.AuthDTO
import br.com.fiap.transactions.dto.JwtDTO
import br.com.fiap.transactions.repository.UserRepository
import br.com.fiap.transactions.security.JwtTokenUtil
import br.com.fiap.transactions.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UserServiceImpl(
        private var authenticationManager: AuthenticationManager,
        private var jwtTokenUtil: JwtTokenUtil
): UserService {

    override fun login(authDTO: AuthDTO): JwtDTO {

        try {
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(authDTO.login, authDTO.password))
        } catch (disabledException: DisabledException) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "User disabled")
        } catch (badCredentialsException: BadCredentialsException) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials")
        }

        val token = jwtTokenUtil.generateToken(authDTO.login)


        return JwtDTO(token)
    }

}