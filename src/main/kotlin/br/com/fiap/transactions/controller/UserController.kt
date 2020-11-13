package br.com.fiap.transactions.controller

import br.com.fiap.transactions.dto.AuthDTO
import br.com.fiap.transactions.dto.JwtDTO
import br.com.fiap.transactions.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("users")
class UserController(
    private var userService: UserService
) {

    @PostMapping("login")
    fun login(@RequestBody authDTO: AuthDTO): JwtDTO = userService.login(authDTO)

}