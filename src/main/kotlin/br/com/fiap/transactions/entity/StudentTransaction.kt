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
        val transactionID: BigInteger,

        @ManyToOne
        @JoinColumn(name = "STUDENT_ID", nullable = false)
        val student: Student,

        @ManyToOne
        @JoinColumn(name = "CARD_ID", nullable = false)
        val card: Card,

        @Column(name= "TRANSACTION_CODE_EXTERNAL", unique = true)
        val transactionCodeExternal: String,

        @Column
        val value: BigDecimal,

        @Column(name= "TRANSACTION_DATE")
        val transactionDate: Timestamp,

        @Column(name = "STATUS")
        val status: String
)