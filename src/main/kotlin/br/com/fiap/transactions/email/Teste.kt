package br.com.fiap.transactions.email

import java.io.BufferedReader
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStreamReader


object Test {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        var reader: BufferedReader? = null
        try {
            reader = BufferedReader(InputStreamReader(FileInputStream("C:\\tmp\\lista_alunos.txt")))
            var next: String
            var line = reader.readLine()
            while (line != null) {
                next = reader.readLine()
                val nextIsBlank = next != null && next.isEmpty()
                println("$line -- next line is blank: $nextIsBlank")
                line = next
            }
        } finally {
            if (reader != null) try {
                reader.close()
            } catch (logOrIgnore: IOException) {
            }
        }
    }
}