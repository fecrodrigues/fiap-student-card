package br.com.fiap.transactions.dto

import java.math.BigInteger

data class StudentCreateUpdateDTO(
        val id: BigInteger?,
        val name: String,
        val className: String,
        val cpf: String,
        val addresId: String,
        val cardId: String
)