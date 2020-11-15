package br.com.fiap.transactions.entity

import javax.persistence.*

@Entity
@Table(name = "USER")
data class User (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @Column
    var login: String,

    @Column
    var password: String

)
