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
        var transactionID: BigInteger?,

        @ManyToOne
        @JoinColumn(name = "STUDENT_ID", nullable = false)
        var student: Student,

        @ManyToOne
        @JoinColumn(name = "CARD_ID", nullable = false)
        var card: Card,

        @Column(name= "TRANSACTION_CODE_EXTERNAL", unique = true)
        var transactionCodeExternal: String,

        @Column
        var value: BigDecimal,

        @Column(name= "TRANSACTION_DATE")
        var transactionDate: Timestamp,

        @Column(name = "STATUS")
        var status: String
)