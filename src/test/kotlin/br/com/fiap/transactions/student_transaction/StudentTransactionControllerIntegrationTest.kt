package br.com.fiap.transactions.student_transaction

import br.com.fiap.transactions.dto.STransactionCancelDTO
import br.com.fiap.transactions.dto.STransactionInsertDTO
import br.com.fiap.transactions.dto.STransactionUpdateStatusDTO
import br.com.fiap.transactions.security.JwtTokenUtil
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.math.BigInteger
import java.util.*

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class StudentTransactionControllerIntegrationTest(
        @Value("\${jwt.secret}") var secret: String,
        @Value("\${jwt.expire}") var expire: Long
) {

    @Autowired
    private lateinit var mvc: MockMvc

    private final val generatedCodeExternal: UUID = UUID.randomUUID()

    @Test
    @WithMockUser(username = "fulano")
    fun insertTransactionsStudent() {

        val jwtTokenUtil = JwtTokenUtil(secret, expire)
        val token: String = jwtTokenUtil.generateToken("fulano")

        val sTransactionInsertDTO = STransactionInsertDTO(
                transactionCodeExternal = generatedCodeExternal.toString(),
                transactionDate = "2020-11-14 16:00:00",
                status = "IN_ANALYSIS",
                value = BigDecimal.valueOf(10.00),
                studentID = BigInteger.ONE,
                cardToken = "0313e412-be96-4d7d-9dfa-2aeb311310ab"
        )

        val gson = Gson()
        val json = gson.toJson(sTransactionInsertDTO)

        val resultActions: ResultActions = mvc.perform(
                MockMvcRequestBuilders.post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer $token")
                        .content(json))
                .andExpect(status().isCreated);

        val result: MvcResult = resultActions.andReturn()
        val contentAsString = result.response.contentAsString

        val jsonObjectReturn = gson.fromJson(contentAsString, JsonObject::class.java)
        val transactionCodeExternal = jsonObjectReturn.get("transactionCodeExternal").asString

        assertEquals(generatedCodeExternal.toString(), transactionCodeExternal)
    }

   @Test
   @WithMockUser(username = "fulano")
   fun getTransactionsByStudent() {

       insertTransactionsStudent()

       val jwtTokenUtil = JwtTokenUtil(secret, expire)
       val token: String = jwtTokenUtil.generateToken("fulano")


       val resultActions: ResultActions = mvc.perform(MockMvcRequestBuilders
               .get("/transactions/student/1")
               .header("Authorization", "Bearer $token")
               .contentType(MediaType.APPLICATION_JSON))
               //.andExpect(status().isOk);

       val result: MvcResult = resultActions.andReturn()
       val contentAsString = result.response.contentAsString

       val gson = Gson()
       val jsonObjectReturn = gson.fromJson(contentAsString, JsonObject::class.java)
       val quantityRegister = jsonObjectReturn.get("totalElements").asInt

       assert(quantityRegister > 0)

   }

    @Test
    @WithMockUser(username = "fulano")
    fun getTransactionsByStudentAndCard() {

        insertTransactionsStudent()

        val jwtTokenUtil = JwtTokenUtil(secret, expire)
        val token: String = jwtTokenUtil.generateToken("fulano")

        val resultActions: ResultActions = mvc.perform(MockMvcRequestBuilders
                .get("/transactions/student/1/card/1")
                .header("Authorization", "Bearer $token")
                .contentType(MediaType.APPLICATION_JSON))
                //.andExpect(status().isOk)


        val result: MvcResult = resultActions.andReturn()
        val contentAsString = result.response.contentAsString

        val gson = Gson()
        val jsonObjectReturn = gson.fromJson(contentAsString, JsonObject::class.java)
        val quantityRegister = jsonObjectReturn.get("totalElements").asInt

        assert(quantityRegister > 0)
    }

    @Test
    @WithMockUser(username = "fulano")
    fun updateTransactionStatus() {

        insertTransactionsStudent()

        val jwtTokenUtil = JwtTokenUtil(secret, expire)
        val token: String = jwtTokenUtil.generateToken("fulano")

        val sTransactionUpdateStatusDTO = STransactionUpdateStatusDTO(
                transactionID = null,
                transactionCodeExternal = generatedCodeExternal.toString(),
                status = "IN_TEST"
        )

        val gson = Gson()
        val json = gson.toJson(sTransactionUpdateStatusDTO)

        val resultActions: ResultActions = mvc.perform(
                MockMvcRequestBuilders.patch("/transactions/status/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer $token")
                        .content(json))
                .andExpect(status().isOk);

        val result: MvcResult = resultActions.andReturn()
        val contentAsString = result.response.contentAsString

        val jsonObjectReturn = gson.fromJson(contentAsString, JsonObject::class.java)
        val newStatus = jsonObjectReturn.get("status").asString

        assertEquals("IN_TEST", newStatus)

    }

    @Test
    @WithMockUser(username = "fulano")
    fun cancelTransaction() {

        insertTransactionsStudent()

        val jwtTokenUtil = JwtTokenUtil(secret, expire)
        val token: String = jwtTokenUtil.generateToken("fulano")

        val sTransactionCancelDTO = STransactionCancelDTO(
                transactionID = null,
                transactionCodeExternal = generatedCodeExternal.toString()
        )

        val gson = Gson()
        val json = gson.toJson(sTransactionCancelDTO)

        mvc.perform(
                MockMvcRequestBuilders.delete("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer $token")
                        .content(json))
                .andExpect(status().isNoContent);

    }

}