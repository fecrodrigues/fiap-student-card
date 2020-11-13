package br.com.fiap.transactions.dto

import java.math.BigDecimal
import java.math.BigInteger

data class STransactionInsertDTO(
        val studentID: BigInteger,
        val cardToken: String,
        val transactionCodeExternal: String,
        val value: BigDecimal,
        val transactionDate: String,
        val status: String = "APPROVED"
)