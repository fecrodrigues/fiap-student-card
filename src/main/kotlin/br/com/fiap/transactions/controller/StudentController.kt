package br.com.fiap.transactions.controller

import br.com.fiap.transactions.dto.StudentCreateUpdateDTO
import br.com.fiap.transactions.dto.StudentDTO
import br.com.fiap.transactions.dto.StudentUpdateCartaoDTO
import br.com.fiap.transactions.service.StudentService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("alunos")
class StudentController(
        private val studentService: StudentService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findAll(): List<StudentDTO?>? {
        return studentService.findAll()
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findById(@PathVariable id: String?): StudentDTO {
        return studentService.findById(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody dto: StudentCreateUpdateDTO?): StudentDTO {
        return studentService.create(dto)
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun update(@PathVariable id: String?, @RequestBody dto: StudentCreateUpdateDTO?): StudentDTO {
        return studentService.update(id, dto)
    }

    @PatchMapping("{id}/cartao")
    @ResponseStatus(HttpStatus.OK)
    fun updateCartao(@PathVariable id: String?, @RequestBody dto: StudentUpdateCartaoDTO?): StudentDTO {
        return studentService.updateCartao(id, dto)
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: String?) {
        studentService.delete(id)
    }
}