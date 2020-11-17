package br.com.fiap.transactions.entity

import br.com.fiap.transactions.dto.StudentCreateUpdateDTO
import java.math.BigInteger
import javax.persistence.*

@Entity
@Table(name = "STUDENT")
data class Student (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: BigInteger?,

    @Column
    var name: String,

    @Column
    var className: String,

    @Column
    var cpf: String,

    @Column(name = "ADDRESS_ID")
    var addresId: String,

    @Column
    var cardId: String
)