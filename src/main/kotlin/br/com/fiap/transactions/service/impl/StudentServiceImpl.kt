package br.com.fiap.transactions.service.impl

import br.com.fiap.transactions.dto.StudentCreateUpdateDTO
import br.com.fiap.transactions.dto.StudentUpdateCartaoDTO
import br.com.fiap.transactions.entity.Student
import br.com.fiap.transactions.repository.StudentRepository
import br.com.fiap.transactions.service.StudentService
import javassist.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigInteger

@Service
class StudentServiceImpl (
        private val studentRepository: StudentRepository
) : StudentService {

    override fun findAll(): List<Student> {
        return studentRepository.findAll()
    }

    override fun findById(id: BigInteger): Student {
        val optStudent =  studentRepository.findById(id)

        if(optStudent.isEmpty){
            throw NotFoundException("Student is not found!")
        }

        return optStudent.get()
    }

    override fun create(dto: StudentCreateUpdateDTO): Student {
        return studentRepository.save(
                Student(null,dto.name,dto.className,dto.cpf,dto.addresId,dto.cardId)
        )
    }

    @Transactional
    override fun update(id: BigInteger, dto: StudentCreateUpdateDTO): Student {
        var uptStudent: Student = findById(id) ?: throw NotFoundException("Student is not found!")


        if(!uptStudent.name.equals(dto.name)){
            uptStudent.name = dto.name
        }
        if(!uptStudent.className.equals(dto.className)){
            uptStudent.className = dto.className
        }
        if(!uptStudent.cpf.equals(dto.cpf)){
            uptStudent.cpf = dto.cpf
        }
        if(!uptStudent.addresId.equals(dto.addresId)){
            uptStudent.addresId = dto.addresId
        }
        if(!uptStudent.cardId.equals(dto.cardId)){
            uptStudent.cardId = dto.cardId
        }

        studentRepository.save(uptStudent)

        return uptStudent
    }

    override fun updateCartao(id: BigInteger, dto: StudentUpdateCartaoDTO): Student {
        val uptStudent = findById(id) ?: throw NotFoundException("Student is not found!")

        if(!uptStudent.cardId.equals(dto.id)){

            uptStudent.cardId = dto.id
            studentRepository.save(uptStudent)
        }

        return uptStudent
    }

    override fun delete(id: BigInteger) {
        studentRepository.deleteById(id)
    }
}