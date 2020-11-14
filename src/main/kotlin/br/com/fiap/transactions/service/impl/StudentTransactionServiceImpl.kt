package br.com.fiap.transactions.service.impl

import br.com.fiap.transactions.dto.STransactionCancelDTO
import br.com.fiap.transactions.dto.STransactionDTO
import br.com.fiap.transactions.dto.STransactionInsertDTO
import br.com.fiap.transactions.dto.STransactionUpdateStatusDTO
import br.com.fiap.transactions.entity.Card
import br.com.fiap.transactions.entity.Student
import br.com.fiap.transactions.repository.StudentTransactionRepository
import br.com.fiap.transactions.service.StudentTransactionService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.math.BigInteger

@Service
class StudentTransactionServiceImpl(
        private val studentTransactionRepository: StudentTransactionRepository
): StudentTransactionService {

    override fun insert(sTransactionInsertDTO: STransactionInsertDTO): Mono<STransactionDTO> {
        TODO("Not yet implemented")
    }

    override fun listByUser(studentID: BigInteger, page: Integer, quantityPerPage: Integer): List<STransactionDTO> {
        var student: Student = Student(id = studentID, name = "", addresId = "0", cardId = "0", className = "", cpf = "")

        val paging: Pageable = PageRequest.of(page.toInt(), quantityPerPage.toInt())

        return studentTransactionRepository
                .findAllByStudent(paging, student)
                .map { STransactionDTO(it) }
    }


    override fun listByUserAndCard(studentID: BigInteger, cardID: BigInteger, page: Integer, quantityPerPage: Integer): List<STransactionDTO> {
        var student: Student = Student(id = studentID, name = "", addresId = "0", cardId = "0", className = "", cpf = "")
        var card: Card = Card(id = cardID, brand = "", cvv = "", number = "", token_card = "", validate = "")

        val paging: Pageable = PageRequest.of(page.toInt(), quantityPerPage.toInt())

        return studentTransactionRepository
                .findAllByStudentAndCard(paging, student, card)
                .map { STransactionDTO(it) }
    }


    override fun cancel(sTransactionCancelDTO: STransactionCancelDTO) {
        TODO("Not yet implemented")
    }

    override fun updateStatus(sTransactionUpdateStatusDTO: STransactionUpdateStatusDTO): Mono<STransactionDTO> {
        TODO("Not yet implemented")
    }


}