package br.com.fiap.transactions.dto

import br.com.fiap.transactions.entity.StudentTransaction
import java.math.BigDecimal
import java.math.BigInteger
import java.sql.Timestamp

data class STransactionDTO (
        var transactionID: BigInteger?,
        var studentID: BigInteger,
        var cardID: BigInteger,
        var transactionCodeExternal: String,
        var value: BigDecimal,
        var transactionDate: Timestamp,
        var status: String
) {
  constructor(studentTransaction: StudentTransaction): this (
          transactionID = studentTransaction.transactionID,
          studentID = studentTransaction.student.id,
          cardID = studentTransaction.card.id,
          transactionCodeExternal = studentTransaction.transactionCodeExternal,
          value = studentTransaction.value,
          transactionDate = studentTransaction.transactionDate,
          status = studentTransaction.status
  )
}