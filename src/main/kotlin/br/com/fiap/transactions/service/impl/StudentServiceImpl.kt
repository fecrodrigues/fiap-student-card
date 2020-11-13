package br.com.fiap.transactions.service.impl

import br.com.fiap.transactions.dto.StudentCreateUpdateDTO
import br.com.fiap.transactions.dto.StudentDTO
import br.com.fiap.transactions.dto.StudentUpdateCartaoDTO
import br.com.fiap.transactions.repository.StudentRepository
import br.com.fiap.transactions.service.StudentService
import org.springframework.stereotype.Service

@Service
class StudentServiceImpl (
        private val studentRepository: StudentRepository
) : StudentService {

    override fun findAll(): List<StudentDTO> {
        TODO("A FAZER")
    }

    override fun findById(id: String?): StudentDTO {
        TODO("A FAZER")
    }

    override fun create(dto: StudentCreateUpdateDTO?): StudentDTO {
        TODO("A FAZER")
    }

    override fun update(id: String?, dto: StudentCreateUpdateDTO?): StudentDTO {
        TODO("A FAZER")
    }

    override fun updateCartao(id: String?, dto: StudentUpdateCartaoDTO?): StudentDTO {
        TODO("A FAZER")
    }

    override fun delete(id: String?) {}
}