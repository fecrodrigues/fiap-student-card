package br.com.fiap.transactions.service.impl

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
import br.com.fiap.transactions.service.StudentTransactionService
import javassist.NotFoundException
import org.aspectj.weaver.ast.Not
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.math.BigInteger
import java.sql.Timestamp
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.function.Supplier


@Service
class StudentTransactionServiceImpl(
        private val studentTransactionRepository: StudentTransactionRepository,
        private val studentRepository: StudentRepository,
        private val cardRepository: CardRepository
): StudentTransactionService {

    override fun insert(sTransactionInsertDTO: STransactionInsertDTO): STransactionDTO {

        var student: Student = studentRepository.findById(sTransactionInsertDTO.studentID).orElseThrow(Supplier { throw NotFoundException("Student not found") })
        var card: Card = cardRepository.findByTokenCard(sTransactionInsertDTO.cardToken).orElseThrow(Supplier { throw NotFoundException("Card not found") })

        var studentTransactionCheck: Optional<StudentTransaction> = studentTransactionRepository
                .findByTransactionCodeExternal(sTransactionInsertDTO.transactionCodeExternal)

        if(studentTransactionCheck.isPresent) {
            throw Exception("transactionCodeExternal already exists")
        }

        lateinit var transactionDate: Timestamp

        try {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
            val parsedDate = dateFormat.parse(sTransactionInsertDTO.transactionDate)
            transactionDate = Timestamp(parsedDate.time)
        } catch (e: Exception) {
            throw ParseException("Invalid Transaction Date format. Correct Format [yyyy-mm-dd hh:mm:ss]", 0)
        }

        val studentTransaction = StudentTransaction(
                transactionID = null,
                student = student,
                card = card,
                transactionCodeExternal = sTransactionInsertDTO.transactionCodeExternal,
                value = sTransactionInsertDTO.value,
                transactionDate = transactionDate,
                status = sTransactionInsertDTO.status
        )

        val savedStudentTransaction: StudentTransaction = studentTransactionRepository.save(studentTransaction)
        return STransactionDTO(savedStudentTransaction)
    }

    override fun listByUser(studentID: BigInteger, page: Integer, quantityPerPage: Integer): Page<STransactionDTO> {
        var student: Student = Student(id = studentID, name = "", addresId = "0", cardId = "0", className = "", cpf = "")

        val paging: Pageable = PageRequest.of(page.toInt() -1, quantityPerPage.toInt())

        return studentTransactionRepository
                .findAllByStudent(paging, student)
                .map { STransactionDTO(it) }
    }


    override fun listByUserAndCard(studentID: BigInteger, cardID: BigInteger, page: Integer, quantityPerPage: Integer): Page<STransactionDTO> {
        var student: Student = Student(id = studentID, name = "", addresId = "0", cardId = "0", className = "", cpf = "")
        var card: Card = Card(id = cardID, brand = "", cvv = "", number = "", tokenCard = "", validate = "")

        val paging: Pageable = PageRequest.of(page.toInt() -1, quantityPerPage.toInt())

        return studentTransactionRepository
                .findAllByStudentAndCard(paging, student, card)
                .map { STransactionDTO(it) }
    }


    override fun cancel(sTransactionCancelDTO: STransactionCancelDTO) {

        var studentTransaction: StudentTransaction = findStudentTransaction(sTransactionCancelDTO.transactionID, sTransactionCancelDTO.transactionCodeExternal)
        if(studentTransaction.status == "CANCELED") throw Exception("transaction already canceled")

        var updated: Int = studentTransactionRepository.updateStatusByTransactionID("CANCELED", studentTransaction.transactionID)

        if(updated != 1) {
            throw Exception("Unable to update transaction")
        }
    }

    private fun findStudentTransaction(transactionID: BigInteger?, transactionExternalCode: String?): StudentTransaction {
        lateinit var studentTransaction: StudentTransaction

        if (transactionID != null) {
            studentTransaction = studentTransactionRepository.findById(transactionID)
                    .orElseThrow(Supplier { NotFoundException("transaction not found") })

        } else if (transactionExternalCode != null) {
            studentTransaction = studentTransactionRepository.findByTransactionCodeExternal(transactionExternalCode)
                    .orElseThrow(Supplier { NotFoundException("transaction not found") })
        } else {
            throw Exception("transaction identifier not informed")
        }

        return studentTransaction
    }

    override fun updateStatus(sTransactionUpdateStatusDTO: STransactionUpdateStatusDTO): STransactionDTO {

        var studentTransaction: StudentTransaction = findStudentTransaction(sTransactionUpdateStatusDTO.transactionID, sTransactionUpdateStatusDTO.transactionCodeExternal)
        if(studentTransaction.status == "CANCELED") throw Exception("Update transaction status not allowed as it is canceled")

        var updated: Int = studentTransactionRepository.updateStatusByTransactionID(sTransactionUpdateStatusDTO.status, studentTransaction.transactionID)

        if(updated != 1) {
            throw Exception("Unable to update transaction status")
        } else {
            studentTransaction.status = sTransactionUpdateStatusDTO.status
        }

        return STransactionDTO(studentTransaction)
    }



}