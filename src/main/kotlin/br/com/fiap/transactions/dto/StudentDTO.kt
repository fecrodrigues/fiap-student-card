package br.com.fiap.transactions.dto

import br.com.fiap.transactions.entity.Student
import java.math.BigInteger
import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

data class StudentDTO(
    val id: BigInteger,
    val name: String,
    val className: String,
    val cpf: String,
    val addresId: String,
    val cardId: String
){
    constructor():this(BigInteger.ONE, "","","","","")
    constructor(student: Student):this(BigInteger.ONE, "","","","",""){}
}