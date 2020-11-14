package br.com.fiap.transactions.controller

import br.com.fiap.transactions.dto.STransactionCancelDTO
import br.com.fiap.transactions.dto.STransactionDTO
import br.com.fiap.transactions.dto.STransactionInsertDTO
import br.com.fiap.transactions.service.StudentTransactionService
import io.swagger.annotations.Api
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import java.math.BigInteger

@RestController
@RequestMapping("transactions")
@Api(tags = ["Student Transaction"])
class StudentTransactionController(
        private val studentTransactionService: StudentTransactionService
) {

    @GetMapping("/student/{studentID}")
    fun listByUser(@PathVariable studentID: BigInteger,
                   @RequestParam(defaultValue = "1", required = false) page: Integer,
                   @RequestParam(defaultValue = "20", required = false) quantityPerPage: Integer): List<STransactionDTO> =
            studentTransactionService.listByUser(studentID, page, quantityPerPage)

    @GetMapping("/student/{studentID}/card/{cardID}")
    fun listByUserAndCard(@PathVariable studentID: BigInteger, @PathVariable cardID: BigInteger,
                          @RequestParam(defaultValue = "1", required = false) page: Integer,
                          @RequestParam(defaultValue = "20", required = false) quantityPerPage: Integer): List<STransactionDTO> =
            studentTransactionService.listByUserAndCard(studentID, cardID, page, quantityPerPage)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun insert(@RequestBody sTransactionInsertDTO: STransactionInsertDTO): Mono<STransactionDTO> =
            studentTransactionService.insert(sTransactionInsertDTO)

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun cancel(@RequestBody sTransactionCancelDTO: STransactionCancelDTO) =
            studentTransactionService.cancel(sTransactionCancelDTO)

}