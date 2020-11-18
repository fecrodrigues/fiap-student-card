package br.com.fiap.transactions.controller

import br.com.fiap.transactions.config.FilePath
import br.com.fiap.transactions.dto.*
import br.com.fiap.transactions.email.CreatePdf
import br.com.fiap.transactions.email.SendEmail
import br.com.fiap.transactions.service.StudentTransactionService
import io.swagger.annotations.Api
import javassist.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.math.BigInteger
import java.text.ParseException

@RestController
@RequestMapping("transactions")
@Api(tags = ["Student Transaction"])
class StudentTransactionController(
        private val studentTransactionService: StudentTransactionService
) {

    @GetMapping("/student/{studentID}")
    fun listByUser(@PathVariable studentID: BigInteger,
                   @RequestParam(defaultValue = "1", required = false) page: Integer,
                   @RequestParam(defaultValue = "20", required = false) quantityPerPage: Integer): Page<STransactionDTO> =
            studentTransactionService.listByUser(studentID, page, quantityPerPage)

    @GetMapping("/student/{studentID}/card/{cardID}")
    fun listByUserAndCard(@PathVariable studentID: BigInteger, @PathVariable cardID: BigInteger,
                          @RequestParam(defaultValue = "1", required = false) page: Integer,
                          @RequestParam(defaultValue = "20", required = false) quantityPerPage: Integer): Page<STransactionDTO> =
            studentTransactionService.listByUserAndCard(studentID, cardID, page, quantityPerPage)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun insert(@RequestBody sTransactionInsertDTO: STransactionInsertDTO): STransactionDTO {
        try {
            return studentTransactionService.insert(sTransactionInsertDTO)
        } catch (nf: NotFoundException) {
            throw ResponseStatusException(
                    HttpStatus.NOT_FOUND, nf.message, nf)
        } catch (pe: ParseException) {
            throw ResponseStatusException(
                    HttpStatus.BAD_REQUEST, pe.message, pe)
        } catch(e: Exception) {
            throw ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.message, e)
        }
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun cancel(@RequestBody sTransactionCancelDTO: STransactionCancelDTO) {
        try {
            return studentTransactionService.cancel(sTransactionCancelDTO)
        }  catch (nf: NotFoundException) {
            throw ResponseStatusException(
                    HttpStatus.NOT_FOUND, nf.message, nf)
        } catch (e: Exception) {
            throw ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.message, e)
        }
    }

    @RequestMapping(value = ["/status/update"], method = arrayOf(RequestMethod.PATCH))
    @ResponseStatus(HttpStatus.OK)
    fun updateStatus(@RequestBody sTransactionUpdateStatusDTO: STransactionUpdateStatusDTO): STransactionDTO {
        try {
            return studentTransactionService.updateStatus(sTransactionUpdateStatusDTO)
        }  catch (nf: NotFoundException) {
            throw ResponseStatusException(
                    HttpStatus.NOT_FOUND, nf.message, nf)
        } catch (e: Exception) {
            throw ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.message, e)
        }
    }

    @PostMapping("/extrato")
    fun sendEmail(@RequestBody extratoEmailDTO: ExtratoEmailDTO) {
        try {
            var result= studentTransactionService.listByUserToPdf(extratoEmailDTO.studentID)
            println(result)
            println(result.size)
            println(result[0].status)
            println(FilePath().filePath(extratoEmailDTO.studentID.toString()))
            CreatePdf().createPdf(result)
            SendEmail().sendEmail(extratoEmailDTO,result)
            //return new ResponseEntity("Email sent with success", HttpStatus.OK);

        }  catch (nf: NotFoundException) {
            throw ResponseStatusException(
                    HttpStatus.NOT_FOUND, nf.message, nf)
        } catch (e: Exception) {
            throw ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.message, e)
        }
    }

/*    @PostMapping("/extrato/bydate")
    fun sendEmailByDate(@RequestBody extratoEmailDTO: ExtratoEmailDTO) {
        try {
            var result= studentTransactionService.listByUserAndDateToPdf(extratoEmailDTO.studentID, extratoEmailDTO.startDateAndTime, extratoEmailDTO.endDateAndTime)
            println(result)
            println(result.size)
            println(result[0].status)
            println(FilePath().filePath(extratoEmailDTO.studentID.toString()))
            CreatePdf().createPdf(result)
            SendEmail().sendEmail(extratoEmailDTO,result)



            //return new ResponseEntity("Email sent with success", HttpStatus.OK);


        } catch (e: Exception) {

        }
    }*/

}

