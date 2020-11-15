package br.com.fiap.transactions.entity

import java.math.BigInteger
import javax.persistence.*

@Entity
@Table(name = "CARD")
data class Card (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: BigInteger,

    @Column(name = "TOKEN_CARD", unique = true)
    val tokenCard: String,

    @Column
    val number: String,

    @Column
    val cvv: String,

    @Column
    val brand: String,

    @Column
    val validate: String
)