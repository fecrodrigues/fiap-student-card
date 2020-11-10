package br.com.fiap.transactions.controller

import br.com.fiap.transactions.dto.STransactionCancelDTO
import br.com.fiap.transactions.dto.STransactionDTO
import br.com.fiap.transactions.dto.STransactionInsertDTO
import br.com.fiap.transactions.service.StudentTransactionService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("transaction")
class StudentTransactionController(
        private val studentTransactionService: StudentTransactionService
) {

    @GetMapping(produces = [MediaType.APPLICATION_STREAM_JSON_VALUE])
    fun listAll(): Flux<STransactionDTO> = studentTransactionService.list()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun insert(@RequestBody sTransactionInsertDTO: STransactionInsertDTO): Mono<STransactionDTO> = studentTransactionService.insert(sTransactionInsertDTO)

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@RequestBody sTransactionCancelDTO: STransactionCancelDTO) = studentTransactionService.delete(sTransactionCancelDTO)

}