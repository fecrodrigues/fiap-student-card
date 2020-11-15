package br.com.fiap.transactions.service

import br.com.fiap.transactions.dto.STransactionCancelDTO
import br.com.fiap.transactions.dto.STransactionDTO
import br.com.fiap.transactions.dto.STransactionInsertDTO
import br.com.fiap.transactions.dto.STransactionUpdateStatusDTO
import org.springframework.data.domain.Page
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.math.BigInteger


interface StudentTransactionService {

    fun insert(sTransactionInsertDTO: STransactionInsertDTO): STransactionDTO
    fun listByUser(studentID: BigInteger, page: Integer, quantityPerPage: Integer): Page<STransactionDTO>
    fun listByUserAndCard(studentID: BigInteger, cardID: BigInteger, page: Integer, quantityPerPage: Integer ): Page<STransactionDTO>
    fun cancel(sTransactionCancelDTO: STransactionCancelDTO)
    fun updateStatus(sTransactionUpdateStatusDTO: STransactionUpdateStatusDTO): STransactionDTO

}