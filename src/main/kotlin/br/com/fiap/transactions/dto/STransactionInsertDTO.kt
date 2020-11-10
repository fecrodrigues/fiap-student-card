package br.com.fiap.transactions.dto

import java.math.BigDecimal
import java.math.BigInteger

data class STransactionInsertDTO(
        val studentID: BigInteger,
        val transactionCodeExternal: String,
        val cardLastDigits: Integer?,
        val cardBrand: String?,
        val value: BigDecimal,
        val transactionDate: String
)