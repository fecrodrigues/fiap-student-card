package br.com.fiap.transactions.repository

import br.com.fiap.transactions.entity.Student
import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository : JpaRepository<Student, Long>