package br.com.fiap.transactions.service

import br.com.fiap.transactions.entity.Student
import br.com.fiap.transactions.repository.StudentRepository
import br.com.fiap.transactions.service.impl.StudentServiceImpl
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigInteger

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
    fun testFindAll_OK(){
        val lista: List<Student> = ArrayList()

        lista.plus(Student(BigInteger.ONE, "", "", "", "", "", ""))
        lista.plus(Student(BigInteger.ONE, "", "", "", "", "", ""))

        Mockito.`when`(repository.findAll()).thenReturn(lista)

        val result = service.findAll()

        assertNotNull(result)
    }
}