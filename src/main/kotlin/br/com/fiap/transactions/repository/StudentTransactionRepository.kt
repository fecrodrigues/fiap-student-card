package br.com.fiap.transactions.repository

import br.com.fiap.transactions.entity.StudentTransaction
import org.springframework.data.repository.Repository
import java.math.BigInteger

interface StudentTransactionRepository: Repository<StudentTransaction, BigInteger>