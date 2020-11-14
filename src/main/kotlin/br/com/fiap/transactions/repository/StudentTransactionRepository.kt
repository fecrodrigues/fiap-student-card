package br.com.fiap.transactions.repository

import br.com.fiap.transactions.entity.Card
import br.com.fiap.transactions.entity.Student
import br.com.fiap.transactions.entity.StudentTransaction
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import java.math.BigInteger

interface StudentTransactionRepository: PagingAndSortingRepository<StudentTransaction, BigInteger> {

    fun findAllByStudent(pageable: Pageable, student: Student): List<StudentTransaction>
    fun findAllByStudentAndCard(pageable: Pageable, student: Student, card: Card): List<StudentTransaction>

}