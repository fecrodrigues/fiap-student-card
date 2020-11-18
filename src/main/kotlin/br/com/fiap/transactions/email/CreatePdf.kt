package br.com.fiap.transactions.email

import br.com.fiap.transactions.config.FilePath
import br.com.fiap.transactions.dto.STransactionDTO
import com.itextpdf.text.*
import com.itextpdf.text.pdf.PdfNumber
import com.itextpdf.text.pdf.PdfPTable
import com.itextpdf.text.pdf.PdfWriter
import java.io.FileOutputStream
import java.io.IOException
import java.time.temporal.ChronoUnit
import java.util.*
import java.util.stream.Stream
import javax.mail.internet.AddressException

class CreatePdf {
    @Throws(AddressException::class, javax.mail.MessagingException::class, IOException::class)
    fun createPdf(message: List<STransactionDTO>) {
        //var PORTRAIT: PdfNumber = PdfNumber(90)
        var document = Document()
        val instance = PdfWriter.getInstance(document, FileOutputStream(FilePath().filePath(message[0].studentID.toString())));
        document.setPageSize(PageSize.A4.rotate())
        document.setMargins(10F, 10F, 20F, 20F)
        document.open ();

        //var bold = Font(Font.FontFamily.HELVETICA, 18, Font.BOLD)
        var paragraph = Paragraph ("User Transactions Report")
        paragraph.setAlignment(Element.ALIGN_CENTER)

        var table = PdfPTable (7);
        table.setWidthPercentage(100F)
        table.setWidths(intArrayOf(12,10,8,30,18,8,14))

        Stream.of("Transaction ID","Student ID", "Card ID", "External Transaction Code", "Transaction Date", "Value", "Status").forEach(table::addCell);

        Arrays.stream(ChronoUnit.values())
        message.size
        for (i in 0 until message.size) {

            table.addCell(message[i].transactionID.toString())
            table.addCell(message[i].studentID.toString())
            table.addCell(message[i].cardID.toString())
            table.addCell(message[i].transactionCodeExternal)
            table.addCell(message[i].transactionDate.toString())
            table.addCell(message[i].value.toString())
            table.addCell(message[i].status)

        }

        paragraph.add(table);
        document.add(paragraph);
        document.close();
        println("PDF Created")
    }
}