package br.com.fiap.transactions.repository

import br.com.fiap.transactions.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.Repository

interface UserRepository : JpaRepository<User, Long> {

    fun findFirstByLogin(login: String): User?

}