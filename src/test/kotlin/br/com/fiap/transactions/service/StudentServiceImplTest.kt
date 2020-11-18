package br.com.fiap.transactions.service

import br.com.fiap.transactions.dto.StudentCreateUpdateDTO
import br.com.fiap.transactions.dto.StudentDTO
import br.com.fiap.transactions.entity.Student
import br.com.fiap.transactions.repository.StudentRepository
import br.com.fiap.transactions.service.impl.StudentServiceImpl
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.mockito.Mockito.`when`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigInteger
import java.util.*
import kotlin.collections.ArrayList

@SpringBootTest
class StudentServiceImplTest{

    @InjectMocks
    private lateinit var  service: StudentServiceImpl

    @Mock
    private lateinit var  repository: StudentRepository

    @BeforeEach
    fun setUp(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testMocks_OK(){
        assertNotNull(this.service)
    }

    @Test
    fun testFindAll(){
        val lista: List<Student> = ArrayList()

        lista.plus(Student(BigInteger.ONE,"Maria Jose","36SCJ","41576185798","784549412","263876286"))
        lista.plus(Student(BigInteger.TWO,"Joao Pedro","25SCJ","78914789639","812168445","389465496"))

        `when`(repository.findAll()).thenReturn(lista)

        val result = service.findAll()

        assertNotNull(result)

        assertEquals(result.get(1).id, BigInteger.TWO)
        assertEquals(result.get(1).cpf, "78914789639")
        assertEquals(result.get(1).cardId, "389465496")
    }

    @Test
    fun testFindById(){
        val bigInteger = BigInteger("123456")
        val std1 = Student(
                null,"Danilo Oliveira","78SCJ",
                "6523185798","8641549412","2637319865"
        )


        `when`(repository.findById(bigInteger)).thenReturn(Optional.of(std1))

        val result: Student = service.findById(bigInteger)

        assertNotNull(result)

        assertEquals(result.id, null)
        assertEquals(result.cpf, "6523185798")
        assertEquals(result.cardId, "2637319865")
    }

    @Test
    fun testCreate(){
        val std1DTO = StudentCreateUpdateDTO(
                null,"Danilo Oliveira","78SCJ",
                "6523185798","8641549412","2637319865"
        )
        val std1 = Student(
                null,"Danilo Oliveira","78SCJ",
                "6523185798","8641549412","2637319865"
        )


        `when`(repository.save(std1)).thenReturn(std1)

        val result: Student = service.create(std1DTO)

        assertNotNull(result)

        assertEquals(result.id, null)
        assertEquals(result.cpf, "6523185798")
        assertEquals(result.cardId, "2637319865")
    }

}