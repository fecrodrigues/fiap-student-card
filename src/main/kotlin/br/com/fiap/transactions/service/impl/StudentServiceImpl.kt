package br.com.fiap.transactions.service.impl

import br.com.fiap.alunocard.dto.request.StudentCreateUpdateDTO
import br.com.fiap.alunocard.dto.request.StudentUpdateCartaoDTO
import br.com.fiap.alunocard.dto.response.StudentDTO
import br.com.fiap.transactions.dto.StudentCreateUpdateDTO
import br.com.fiap.transactions.dto.StudentDTO
import br.com.fiap.transactions.dto.StudentUpdateCartaoDTO
import br.com.fiap.transactions.service.StudentService
import org.springframework.stereotype.Service

@Service
class StudentServiceImpl (
        private val studentRepository: StudentRepository
) : StudentService {

    override fun findAll(): List<StudentDTO>? {
        return null
    }

    override fun findById(id: String?): StudentDTO? {
        return null
    }

    override fun create(dto: StudentCreateUpdateDTO?): StudentDTO? {
        return null
    }

    override fun update(id: String?, dto: StudentCreateUpdateDTO?): StudentDTO? {
        return null
    }

    override fun updateCartao(id: String?, dto: StudentUpdateCartaoDTO?): StudentDTO? {
        return null
    }

    override fun delete(id: String?) {}
}