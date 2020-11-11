package br.com.fiap.transactions.entity

import java.math.BigInteger
import javax.persistence.*

@Entity
@Table(name = "CARD")
data class Card (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: BigInteger,

    @Column
    private val number: String,

    @Column
    private val cvv: String,

    @Column
    private val brand: String,

    @Column
    private val validate: String
)