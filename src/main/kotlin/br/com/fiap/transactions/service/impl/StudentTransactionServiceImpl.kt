package br.com.fiap.transactions.service.impl

import br.com.fiap.transactions.dto.STransactionCancelDTO
import br.com.fiap.transactions.dto.STransactionDTO
import br.com.fiap.transactions.dto.STransactionInsertDTO
import br.com.fiap.transactions.entity.StudentTransaction
import br.com.fiap.transactions.repository.StudentTransactionRepository
import br.com.fiap.transactions.service.StudentTransactionService
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class StudentTransactionServiceImpl(
        private val studentTransactionRepository: StudentTransactionRepository
): StudentTransactionService {

    override fun insert(sTransactionInsertDTO: STransactionInsertDTO): Mono<STransactionDTO> {
        TODO("Not yet implemented")
    }

    override fun list(): Flux<STransactionDTO> {
        TODO("Not yet implemented")
    }

    override fun delete(sTransactionCancelDTO: STransactionCancelDTO) {
        TODO("Not yet implemented")
    }

}