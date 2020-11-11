package br.com.fiap.transactions.repository

import br.com.fiap.alunocard.entity.Student
import br.com.fiap.transactions.entity.Student
import org.springframework.data.jpa.repository.JpaRepository

internal interface StudentRepository : JpaRepository<Student?, Long?>