package br.com.fiap.transactions.controller

import br.com.fiap.transactions.dto.StudentCreateUpdateDTO
import br.com.fiap.transactions.dto.StudentDTO
import br.com.fiap.transactions.dto.StudentUpdateCartaoDTO
import br.com.fiap.transactions.service.StudentService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.math.BigInteger
import java.util.ArrayList

@RestController
@RequestMapping("alunos")
class StudentController(
        private val studentService: StudentService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findAll(): List<StudentDTO?> {
        val studentsDTO: List<StudentDTO> = ArrayList()
        val students = studentService.findAll()

        for(std in students){
            studentsDTO.plus(StudentDTO(std))
        }
        
        return studentsDTO
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findById(@PathVariable id: BigInteger): StudentDTO {
        return StudentDTO(studentService.findById(id))
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody dto: StudentCreateUpdateDTO): StudentDTO {
        return StudentDTO(studentService.create(dto))
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun update(@PathVariable id: BigInteger, @RequestBody dto: StudentCreateUpdateDTO): StudentDTO {
        return StudentDTO(studentService.update(id, dto))
    }

    @PatchMapping("{id}/cartao")
    @ResponseStatus(HttpStatus.OK)
    fun updateCartao(@PathVariable id: BigInteger, @RequestBody dto: StudentUpdateCartaoDTO): StudentDTO {
        return StudentDTO(studentService.updateCartao(id, dto))
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: BigInteger) {
        studentService.delete(id)
    }
}