package br.com.fiap.transactions.dto

import java.math.BigInteger

data class STransactionCancelDTO(
        val transactionID: BigInteger?,
        val transactionExternalCode: String?
)