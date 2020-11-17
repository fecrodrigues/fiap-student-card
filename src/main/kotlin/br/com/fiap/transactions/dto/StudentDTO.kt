package br.com.fiap.transactions.dto

import br.com.fiap.transactions.entity.Student
import java.math.BigInteger

data class StudentDTO(
        val id: BigInteger?,
        val name: String,
        val className: String,
        val cpf: String,
        val addresId: String,
        val cardId: String
){
    constructor(student: Student):this(student.id, student.name,student.className,student.cpf,student.addresId,student.cardId)
}