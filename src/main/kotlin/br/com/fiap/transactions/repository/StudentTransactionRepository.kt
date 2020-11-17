package br.com.fiap.transactions.repository

import br.com.fiap.transactions.dto.STransactionDTO
import br.com.fiap.transactions.entity.Card
import br.com.fiap.transactions.entity.Student
import br.com.fiap.transactions.entity.StudentTransaction
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import java.math.BigInteger
import java.util.*
import javax.transaction.Transactional

interface StudentTransactionRepository: PagingAndSortingRepository<StudentTransaction, BigInteger> {

    fun findAllByStudent(pageable: Pageable, student: Student): Page<StudentTransaction>
    fun findAllByStudentAndCard(pageable: Pageable, student: Student, card: Card): Page<StudentTransaction>
    fun findByTransactionCodeExternal(transactionCodeExternal: String?): Optional<StudentTransaction>

    @Transactional
    @Modifying
    @Query("update StudentTransaction st set st.status = ?1 where st.transactionID = ?2")
    fun updateStatusByTransactionID(status: String?, id: BigInteger?): Int

    @Query("select * from student_transaction where student_id = ?1", nativeQuery = true)
    fun findAllByStudentToPdf(student: Student?): List<StudentTransaction>

/*    @Query("select * from student_transaction where student_id = ?1 and (" +
            "transaction_date >= ?2 and transaction_date <= ?3", nativeQuery = true)
    fun findAllByStudentAndDateToPdf(student: Student?, startDateAndTime: String, endDateAndTime: String): List<StudentTransaction>*/

}