package br.com.fiap.transactions.student_transaction

import br.com.fiap.transactions.dto.STransactionCancelDTO
import br.com.fiap.transactions.dto.STransactionDTO
import br.com.fiap.transactions.dto.STransactionInsertDTO
import br.com.fiap.transactions.dto.STransactionUpdateStatusDTO
import br.com.fiap.transactions.entity.Card
import br.com.fiap.transactions.entity.Student
import br.com.fiap.transactions.entity.StudentTransaction
import br.com.fiap.transactions.repository.CardRepository
import br.com.fiap.transactions.repository.StudentRepository
import br.com.fiap.transactions.repository.StudentTransactionRepository
import br.com.fiap.transactions.service.impl.StudentTransactionServiceImpl
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.stubbing.Answer
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import java.math.BigDecimal
import java.math.BigInteger
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*


@ExtendWith(MockitoExtension::class)
class StudentTransactionServiceUnitTests {

    @Mock
    private lateinit var studentTransactionRepository: StudentTransactionRepository

    @Mock
    private lateinit var studentRepository: StudentRepository

    @Mock
    private lateinit var cardRepository: CardRepository

    @InjectMocks
    private lateinit var studentTransactionService: StudentTransactionServiceImpl

    private final val generatedCodeExternal: UUID = UUID.randomUUID()

    @Test
    fun whenSaveTransaction() {

        val student = Student(id = BigInteger.ONE, name = "", className = "", cpf = "", addresId = "", cardId = "", ra="")
        val card = Card(tokenCard = "0313e412-be96-4d7d-9dfa-2aeb311310ab", id = BigInteger.ONE, validate = "", number = "", cvv = "", brand = "")

        val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        val parsedDate = dateFormat.parse("2020-02-10 13:20:44")
        val transactionDate = Timestamp(parsedDate.time)

        val studentTransaction = StudentTransaction(
                transactionID = null,
                transactionCodeExternal = generatedCodeExternal.toString(),
                transactionDate = transactionDate,
                status = "IN_ANALYSIS",
                value = BigDecimal.valueOf(10.00),
                student = student,
                card = card
        )

        given(studentRepository.findById(BigInteger.ONE)).willReturn(Optional.of(student))
        given(cardRepository.findByTokenCard(card.tokenCard)).willReturn(Optional.of(card))
        given(studentTransactionRepository.save(studentTransaction)).willAnswer(Answer { it.getArgument(0, StudentTransaction::class.java) })

        val sTransactionInsertDTO = STransactionInsertDTO(
                studentID = BigInteger.ONE,
                value = BigDecimal.valueOf(10.00),
                status = "IN_ANALYSIS",
                transactionDate = "2020-02-10 13:20:44",
                transactionCodeExternal = generatedCodeExternal.toString(),
                cardToken = "0313e412-be96-4d7d-9dfa-2aeb311310ab"
        )

        val savedStudentTransaction: STransactionDTO = studentTransactionService.insert(sTransactionInsertDTO)

        assert(savedStudentTransaction !== null)

    }

    @Test
    fun whenFindTransactionByStudent() {

        val student = Student(id = BigInteger.ONE, name = "", className = "", cpf = "", addresId = "0", cardId = "0", ra="")
        val paging: Pageable = PageRequest.of(0, 10)

        val card = Card(tokenCard = "0313e412-be96-4d7d-9dfa-2aeb311310ab", id = BigInteger.ONE, validate = "", number = "", cvv = "", brand = "")

        val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        val parsedDate = dateFormat.parse("2020-02-10 13:20:44")
        val transactionDate = Timestamp(parsedDate.time)

        val studentTransactions: MutableList<StudentTransaction> = ArrayList()
        studentTransactions.add(
                StudentTransaction(
                        transactionID = BigInteger.valueOf(999),
                        transactionCodeExternal = generatedCodeExternal.toString(),
                        transactionDate = transactionDate,
                        status = "IN_ANALYSIS",
                        value = BigDecimal.valueOf(10.00),
                        student = student,
                        card = card
                )
        )

        val mockListStudent: Page<StudentTransaction> = PageImpl(studentTransactions)
        given(studentTransactionRepository.findAllByStudent(paging, student)).willReturn(mockListStudent)

        val listStudentTransaction: Page<STransactionDTO> = studentTransactionService.listByUser(student.id!!, Integer(1), Integer(10))
        assert(listStudentTransaction.totalElements == 1L)

    }

    @Test
    fun whenFindTransactionByStudentAndCard() {

        val student = Student(id = BigInteger.ONE, name = "", className = "", cpf = "", addresId = "0", cardId = "0", ra="")
        val paging: Pageable = PageRequest.of(0, 10)

        val card = Card(tokenCard = "", id = BigInteger.ONE, validate = "", number = "", cvv = "", brand = "")

        val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        val parsedDate = dateFormat.parse("2020-02-10 13:20:44")
        val transactionDate = Timestamp(parsedDate.time)

        val studentTransactions: MutableList<StudentTransaction> = ArrayList()
        studentTransactions.add(
                StudentTransaction(
                        transactionID = BigInteger.valueOf(999),
                        transactionCodeExternal = generatedCodeExternal.toString(),
                        transactionDate = transactionDate,
                        status = "IN_ANALYSIS",
                        value = BigDecimal.valueOf(10.00),
                        student = student,
                        card = card
                )
        )

        val mockListStudent: Page<StudentTransaction> = PageImpl(studentTransactions)
        given(studentTransactionRepository.findAllByStudentAndCard(paging, student, card)).willReturn(mockListStudent)

        val listStudentTransaction: Page<STransactionDTO> = studentTransactionService.listByUserAndCard(student.id!!, card.id, Integer(1), Integer(10))
        assert(listStudentTransaction.totalElements == 1L)

    }

    @Test
    fun whenCancel() {

        val student = Student(id = BigInteger.ONE, name = "", className = "", cpf = "", addresId = "", cardId = "", ra="")
        val card = Card(tokenCard = "0313e412-be96-4d7d-9dfa-2aeb311310ab", id = BigInteger.ONE, validate = "", number = "", cvv = "", brand = "")

        val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        val parsedDate = dateFormat.parse("2020-02-10 13:20:44")
        val transactionDate = Timestamp(parsedDate.time)

        val studentTransaction = StudentTransaction(
                transactionID = BigInteger.valueOf(99),
                transactionCodeExternal = generatedCodeExternal.toString(),
                transactionDate = transactionDate,
                status = "IN_ANALYSIS",
                value = BigDecimal.valueOf(10.00),
                student = student,
                card = card
        )

        given(studentTransactionRepository.findById(BigInteger.valueOf(99))).willReturn(Optional.of(studentTransaction))
        given(studentTransactionRepository.updateStatusByTransactionID("CANCELED", BigInteger.valueOf(99))).willReturn(1)

        val sTransactionCancelDTO = STransactionCancelDTO(
                transactionID = BigInteger.valueOf(99),
                transactionCodeExternal = generatedCodeExternal.toString()
        )

        studentTransactionService.cancel(sTransactionCancelDTO)

    }

    @Test
    fun whenUpdateStatusTransactionCodeExternal() {

        val student = Student(id = BigInteger.ONE, name = "", className = "", cpf = "", addresId = "", cardId = "", ra="")
        val card = Card(tokenCard = "0313e412-be96-4d7d-9dfa-2aeb311310ab", id = BigInteger.ONE, validate = "", number = "", cvv = "", brand = "")

        val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        val parsedDate = dateFormat.parse("2020-02-10 13:20:44")
        val transactionDate = Timestamp(parsedDate.time)

        val studentTransaction = StudentTransaction(
                transactionID = null,
                transactionCodeExternal = generatedCodeExternal.toString(),
                transactionDate = transactionDate,
                status = "IN_ANALYSIS",
                value = BigDecimal.valueOf(10.00),
                student = student,
                card = card
        )

        val sTransactionUpdateStatusDTO = STransactionUpdateStatusDTO(
                transactionID = null,
                status = "IN_TEST",
                transactionCodeExternal = generatedCodeExternal.toString()
        )

        given(studentTransactionRepository.findByTransactionCodeExternal(generatedCodeExternal.toString()))
                .willReturn(Optional.of(studentTransaction))

        given(studentTransactionRepository
                .updateStatusByTransactionID(sTransactionUpdateStatusDTO.status, sTransactionUpdateStatusDTO.transactionID))
                .willReturn(1)

        val updatedStudentTransaction: STransactionDTO = studentTransactionService.updateStatus(sTransactionUpdateStatusDTO)
        assert(updatedStudentTransaction.status == "IN_TEST")

    }

    @Test
    fun whenUpdateStatusTransactionID() {

        val student = Student(id = BigInteger.ONE, name = "", className = "", cpf = "", addresId = "", cardId = "", ra="")
        val card = Card(tokenCard = "0313e412-be96-4d7d-9dfa-2aeb311310ab", id = BigInteger.ONE, validate = "", number = "", cvv = "", brand = "")

        val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        val parsedDate = dateFormat.parse("2020-02-10 13:20:44")
        val transactionDate = Timestamp(parsedDate.time)

        val studentTransaction = StudentTransaction(
                transactionID = BigInteger.valueOf(99),
                transactionCodeExternal = generatedCodeExternal.toString(),
                transactionDate = transactionDate,
                status = "IN_ANALYSIS",
                value = BigDecimal.valueOf(10.00),
                student = student,
                card = card
        )

        val sTransactionUpdateStatusDTO = STransactionUpdateStatusDTO(
                transactionID = BigInteger.valueOf(99),
                status = "IN_TEST",
                transactionCodeExternal = null
        )

        given(studentTransactionRepository.findById(BigInteger.valueOf(99)))
                .willReturn(Optional.of(studentTransaction))

        given(studentTransactionRepository
                .updateStatusByTransactionID(sTransactionUpdateStatusDTO.status, sTransactionUpdateStatusDTO.transactionID))
                .willReturn(1)

        val updatedStudentTransaction: STransactionDTO = studentTransactionService.updateStatus(sTransactionUpdateStatusDTO)
        assert(updatedStudentTransaction.status == "IN_TEST")

    }



}