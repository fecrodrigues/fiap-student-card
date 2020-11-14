package br.com.fiap.transactions.service

import br.com.fiap.transactions.dto.STransactionCancelDTO
import br.com.fiap.transactions.dto.STransactionDTO
import br.com.fiap.transactions.dto.STransactionInsertDTO
import br.com.fiap.transactions.dto.STransactionUpdateStatusDTO
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.math.BigInteger


interface StudentTransactionService {

    fun insert(sTransactionInsertDTO: STransactionInsertDTO): Mono<STransactionDTO>
    fun listByUser(studentID: BigInteger, page: Integer, quantityPerPage: Integer): List<STransactionDTO>
    fun listByUserAndCard(studentID: BigInteger, cardID: BigInteger, page: Integer, quantityPerPage: Integer ): List<STransactionDTO>
    fun cancel(sTransactionCancelDTO: STransactionCancelDTO)
    fun updateStatus(sTransactionUpdateStatusDTO: STransactionUpdateStatusDTO): Mono<STransactionDTO>

}