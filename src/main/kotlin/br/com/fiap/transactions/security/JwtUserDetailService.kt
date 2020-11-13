package br.com.fiap.transactions.security

import br.com.fiap.transactions.entity.User
import br.com.fiap.transactions.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class JwtUserDetailService(
        var userRepository: UserRepository
) : UserDetailsService {


    override fun loadUserByUsername(username: String): UserDetails {

        var user: User? = userRepository.findFirstByLogin(username) ?: throw Exception("""User $username not found""")

        return org.springframework.security.core.userdetails.User(
                user?.login,
                user?.password,
                ArrayList()
        )

    }

}