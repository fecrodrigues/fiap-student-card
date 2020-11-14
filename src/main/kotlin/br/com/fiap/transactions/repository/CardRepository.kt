package br.com.fiap.transactions.repository

import br.com.fiap.transactions.entity.Card
import org.springframework.data.jpa.repository.JpaRepository
import java.math.BigInteger
import java.util.*

interface CardRepository: JpaRepository<Card, BigInteger> {

    fun findByTokenCard(tokenCard: String): Optional<Card>

}