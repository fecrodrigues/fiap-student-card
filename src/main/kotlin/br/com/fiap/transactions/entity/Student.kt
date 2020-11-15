package br.com.fiap.transactions.entity

import br.com.fiap.transactions.dto.StudentCreateUpdateDTO
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
){
    constructor():this(BigInteger.ONE,"","","","",""){}
    constructor(dto: StudentCreateUpdateDTO):this(BigInteger.ONE,"","","","",""){}
}