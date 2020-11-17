package br.com.fiap.transactions.controller

import br.com.fiap.transactions.dto.StudentCreateUpdateDTO
import br.com.fiap.transactions.dto.StudentDTO
import br.com.fiap.transactions.dto.StudentUpdateCartaoDTO
import br.com.fiap.transactions.service.StudentService
import javassist.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
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
        try{
            return StudentDTO(studentService.findById(id))
        } catch (nf: NotFoundException){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, nf.message, nf)
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody dto: StudentCreateUpdateDTO): StudentDTO {
        try{
            return StudentDTO(studentService.create(dto))
        }catch (ex: Exception){
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.message, ex)
        }
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun update(@PathVariable id: BigInteger, @RequestBody dto: StudentCreateUpdateDTO): StudentDTO {
        try{
            return StudentDTO(studentService.update(id, dto))
        } catch (nf: NotFoundException){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, nf.message, nf)
        }catch (ex: Exception){
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.message, ex)
        }
    }

    @PatchMapping("{id}/cartao")
    @ResponseStatus(HttpStatus.OK)
    fun updateCartao(@PathVariable id: BigInteger, @RequestBody dto: StudentUpdateCartaoDTO): StudentDTO {
        try{
            return StudentDTO(studentService.updateCartao(id, dto))
        } catch (nf: NotFoundException){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, nf.message, nf)
        }catch (ex: Exception){
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.message, ex)
        }
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: BigInteger) {
        try{
            studentService.delete(id)
        }catch (ex: Exception){
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.message, ex)
        }
    }
}