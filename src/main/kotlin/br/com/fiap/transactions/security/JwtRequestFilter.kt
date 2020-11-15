package br.com.fiap.transactions.security

import io.jsonwebtoken.ExpiredJwtException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtRequestFilter(
        var jwtUserDetailService: JwtUserDetailService,
        var jwtTokenUtil: JwtTokenUtil
) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {

        var token: String? = request.getHeader("Authorization")

        if(token != null && token.startsWith("Bearer ")) {

            try {
                var login: String? = jwtTokenUtil.getLoginFromToken(token)

                if(login != null && SecurityContextHolder.getContext().authentication == null) {
                    var userDetails: UserDetails  = jwtUserDetailService.loadUserByUsername(login);

                    var userToken: UsernamePasswordAuthenticationToken =
                        UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities);

                    userToken.details = WebAuthenticationDetailsSource()
                            .buildDetails(request);

                    SecurityContextHolder.getContext().authentication = userToken
                }

            } catch (illegalArgumentException: IllegalArgumentException) {
                logger.info(illegalArgumentException.message);
            } catch (expiredJwtException: ExpiredJwtException) {
                logger.info(expiredJwtException.message);
            }

        } else {
            logger.warn("JWT Token not passed or invalid");
        }

        filterChain.doFilter(request, response);

    }

}