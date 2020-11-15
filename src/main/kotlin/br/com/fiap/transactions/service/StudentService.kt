package br.com.fiap.transactions.service
import br.com.fiap.transactions.dto.StudentCreateUpdateDTO
import br.com.fiap.transactions.dto.StudentDTO
import br.com.fiap.transactions.dto.StudentUpdateCartaoDTO
import br.com.fiap.transactions.entity.Student
import java.math.BigInteger

interface StudentService {

    fun findAll(): List<Student>
    fun findById(id: BigInteger): Student
    fun create(dto: StudentCreateUpdateDTO): Student
    fun update(id: BigInteger, dto: StudentCreateUpdateDTO): Student
    fun updateCartao(id: BigInteger, dto: StudentUpdateCartaoDTO): Student
    fun delete(id: BigInteger)
}