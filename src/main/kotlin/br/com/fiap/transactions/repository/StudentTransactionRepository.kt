package br.com.fiap.transactions.repository

import br.com.fiap.transactions.entity.Student
import br.com.fiap.transactions.entity.StudentTransaction
import org.springframework.data.repository.Repository
import reactor.core.publisher.Flux
import java.math.BigInteger

interface StudentTransactionRepository: Repository<StudentTransaction, BigInteger> {

    fun findAllByStudent(student: Student): List<StudentTransaction>;
    fun findAllByStudentAndCard(studentID: BigInteger, cardID: BigInteger): List<StudentTransaction>

}