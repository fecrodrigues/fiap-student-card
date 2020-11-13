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
        @Column(name = "TRANSACTION_ID")
        private var transactionID: BigInteger,

        @ManyToOne
        @JoinColumn(name = "ID", nullable = false)
        private var student: Student,

        @ManyToOne
        @JoinColumn(name = "TOKEN_CARD", nullable = false)
        private var card: Card,

        @Column(name= "TRANSACTION_CODE_EXTERNAL", unique = true)
        private var transactionCodeExternal: String,

        @Column
        private var value: BigDecimal,

        @Column(name= "TRANSACTION_DATE")
        private var transactionDate: Timestamp
)