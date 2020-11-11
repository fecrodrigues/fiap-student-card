package br.com.fiap.transactions.entity

import java.math.BigInteger
import javax.persistence.*

@Entity
data class Addres (

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: BigInteger,

    @Column
    private val street: String,

    @Column
    private val neighborhood: String,

    @Column
    private val complement: String,

    @Column
    private val number: String,

    @Column
    private val cep: String,

    @Column
    private val city: String,

    @Column
    private val uf: String? = null
)