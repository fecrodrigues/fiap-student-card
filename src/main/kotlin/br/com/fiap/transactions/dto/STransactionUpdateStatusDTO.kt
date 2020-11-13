package br.com.fiap.transactions.dto

import java.math.BigInteger

data class STransactionUpdateStatusDTO(
        val transactionID: BigInteger?,
        val transactionExternalCode: String?,
        val status: String
)