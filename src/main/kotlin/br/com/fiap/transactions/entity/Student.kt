package br.com.fiap.transactions.entity

import java.math.BigInteger
import javax.persistence.*

@Entity
@Table(name = "STUDENT")
data class Student (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: BigInteger,

    @Column
    private val name: String,

    @Column
    private val className: String,

    @Column
    private val cpf: String,

    @Column
    private val addresId: String,

    @Column
    private val cardId: String
)