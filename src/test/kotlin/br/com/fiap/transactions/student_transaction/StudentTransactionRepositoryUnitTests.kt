package br.com.fiap.transactions.student_transaction

import br.com.fiap.transactions.entity.Card
import br.com.fiap.transactions.entity.Student
import br.com.fiap.transactions.entity.StudentTransaction
import br.com.fiap.transactions.repository.StudentTransactionRepository
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.math.BigInteger
import java.sql.Timestamp
import java.util.*

@Transactional
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StudentTransactionRepositoryUnitTests {

    @Autowired
    private lateinit var  repository: StudentTransactionRepository

    private final val generatedCodeExternal: UUID = UUID.randomUUID()

    @Test
    fun whenInsertRepository(): BigInteger? {

        val student = Student(id = BigInteger.ONE, name = "", cpf = "", className = "", cardId = "", addresId = "")
        val card = Card(tokenCard = "0313e412-be96-4d7d-9dfa-2aeb311310ab", id = BigInteger.ONE, validate = "", number = "", cvv = "", brand = "")


        val studentTransaction = StudentTransaction(
                transactionID = null,
                transactionCodeExternal = generatedCodeExternal.toString(),
                transactionDate = Timestamp(System.currentTimeMillis()),
                status = "IN_ANALYSIS",
                value = BigDecimal.valueOf(10.00),
                student = student,
                card = card
        )

        val result = repository.save(studentTransaction)

        assertNotNull(result.transactionID)

        return result.transactionID
    }

    @Test
    fun whenFindByStudentRepository(){

        val student = Student(id = BigInteger.ONE, name = "", cpf = "", className = "", cardId = "", addresId = "")
        val paging: Pageable = PageRequest.of(0, 10)

        val result = repository.findAllByStudent(paging, student)
        assert(result.totalElements > 0)
    }

    @Test
    fun whenFindByStudentAndCardRepository(){

        val student = Student(id = BigInteger.ONE, name = "", cpf = "", className = "", cardId = "", addresId = "")
        val card = Card(tokenCard = "0313e412-be96-4d7d-9dfa-2aeb311310ab", id = BigInteger.ONE, validate = "", number = "", cvv = "", brand = "")
        val paging: Pageable = PageRequest.of(0, 10)

        val result = repository.findAllByStudentAndCard(paging, student, card)
        assert(result.totalElements > 0)
    }

    @Test
    fun whenFindByTransactionCode(){

        whenInsertRepository()

        val result = repository.findByTransactionCodeExternal(generatedCodeExternal.toString())
        assertEquals(generatedCodeExternal.toString(), result.get().transactionCodeExternal)
    }

    @Test
    fun whenUpdateTransactionStatus(){

        var transactionID: BigInteger? = whenInsertRepository()

        val result = repository.updateStatusByTransactionID("IN_TEST", transactionID)
        assert(result == 1)
    }

}