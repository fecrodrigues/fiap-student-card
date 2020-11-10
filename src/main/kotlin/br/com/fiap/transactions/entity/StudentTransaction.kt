package br.com.fiap.transactions.entity

import java.math.BigDecimal
import java.math.BigInteger
import java.sql.Timestamp
import javax.persistence.*

@Entity
@Table(name = "STUDENT_TRANSACTION")
data class StudentTransaction (

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private var transactionID: BigInteger,

        @Column
        private var studentID: BigInteger,

        @Column
        private var transactionCodeExternal: String,

        @Column
        private var cardLastDigits: Integer,

        @Column
        private var cardBrand: String,

        @Column
        private var value: BigDecimal,

        @Column
        private var transactionDate: Timestamp
)