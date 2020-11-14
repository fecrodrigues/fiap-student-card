package br.com.fiap.transactions.entity

import java.math.BigInteger
import javax.persistence.*

@Entity
@Table(name = "STUDENT")
data class Student (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: BigInteger,

    @Column
    val name: String,

    @Column
    val className: String,

    @Column
    val cpf: String,

    @Column(name = "ADDRESS_ID")
    val addresId: String,

    @Column
    val cardId: String
)