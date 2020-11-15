package br.com.fiap.transactions.dto

import br.com.fiap.transactions.entity.Student

data class StudentDTO(
        val name: String
){
    constructor(student: Student):this(""){}
}