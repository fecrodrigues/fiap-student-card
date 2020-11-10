package br.com.fiap.transactions.service

import br.com.fiap.transactions.dto.STransactionCancelDTO
import br.com.fiap.transactions.dto.STransactionDTO
import br.com.fiap.transactions.dto.STransactionInsertDTO
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


interface StudentTransactionService {

    fun insert(sTransactionInsertDTO: STransactionInsertDTO): Mono<STransactionDTO>
    fun list(): Flux<STransactionDTO>
    fun delete(sTransactionCancelDTO: STransactionCancelDTO)

}