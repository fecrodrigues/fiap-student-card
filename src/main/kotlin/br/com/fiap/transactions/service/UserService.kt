package br.com.fiap.transactions.service

import br.com.fiap.transactions.dto.AuthDTO
import br.com.fiap.transactions.dto.JwtDTO

interface UserService {

    fun login(authDTO: AuthDTO): JwtDTO

}