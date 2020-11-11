package br.com.fiap.transactions.service

import br.com.fiap.alunocard.dto.request.StudentCreateUpdateDTO
import br.com.fiap.alunocard.dto.request.StudentUpdateCartaoDTO
import br.com.fiap.alunocard.dto.response.StudentDTO
import br.com.fiap.transactions.dto.StudentCreateUpdateDTO
import br.com.fiap.transactions.dto.StudentDTO
import br.com.fiap.transactions.dto.StudentUpdateCartaoDTO

interface StudentService {
    fun findAll(): List<StudentDTO?>?
    fun findById(id: String?): StudentDTO?
    fun create(dto: StudentCreateUpdateDTO?): StudentDTO?
    fun update(id: String?, dto: StudentCreateUpdateDTO?): StudentDTO?
    fun updateCartao(id: String?, dto: StudentUpdateCartaoDTO?): StudentDTO?
    fun delete(id: String?)
}