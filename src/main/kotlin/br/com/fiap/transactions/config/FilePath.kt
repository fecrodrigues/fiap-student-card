package br.com.fiap.transactions.config

import java.time.LocalDate
import java.time.ZoneId
import java.util.*

class FilePath {
    fun filePath(studentID: String): String {
        val pathBuilder = StringBuilder()
        pathBuilder.append("C:/tmp/")
                .append(studentID)
                .append("-")
                .append(LocalDate.now(ZoneId.of("GMT-03:00")))
                .append(".pdf")
        return pathBuilder.toString()

    }
}