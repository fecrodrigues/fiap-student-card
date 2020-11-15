package br.com.fiap.transactions.service.impl

import br.com.fiap.transactions.dto.StudentCreateUpdateDTO
import br.com.fiap.transactions.dto.StudentDTO
import br.com.fiap.transactions.dto.StudentUpdateCartaoDTO
import br.com.fiap.transactions.entity.Student
import br.com.fiap.transactions.repository.StudentRepository
import br.com.fiap.transactions.service.StudentService
import org.springframework.stereotype.Service
import java.math.BigInteger
import java.util.*

@Service
class StudentServiceImpl (
        private val studentRepository: StudentRepository
) : StudentService {

    override fun findAll(): List<Student> {
        return studentRepository.findAll()
    }

    override fun findById(id: BigInteger): Student {
        val optStudent =  studentRepository.findById(id)

        return optStudent.get()
    }

    override fun create(dto: StudentCreateUpdateDTO): Student {
        return studentRepository.save(Student(dto))
    }

    override fun update(id: BigInteger, dto: StudentCreateUpdateDTO): Student {
        val uptStudent = findById(id)

        return uptStudent
    }

    override fun updateCartao(id: BigInteger, dto: StudentUpdateCartaoDTO): Student {
        val uptStudent = findById(id)

        return uptStudent
    }

    override fun delete(id: BigInteger) {
        studentRepository.deleteById(id)
    }
}