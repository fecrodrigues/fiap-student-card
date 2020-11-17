package br.com.fiap.transactions.email

import br.com.fiap.transactions.config.Credentials
import br.com.fiap.transactions.config.FilePath
import br.com.fiap.transactions.dto.ExtratoEmailDTO
import br.com.fiap.transactions.dto.STransactionDTO
import java.io.IOException
import java.util.*
import javax.mail.Authenticator
import javax.mail.Message
import javax.mail.Multipart
import javax.mail.Transport
import javax.mail.internet.*


class SendEmail {
    @Throws(AddressException::class, javax.mail.MessagingException::class, IOException::class)
    fun sendEmail(extratoEmailDTO: ExtratoEmailDTO, message: List<STransactionDTO>) {
        val props = Properties()
        props.put("mail.smtp.auth", "true")
        props.put("mail.smtp.starttls.enable", "true")
        props.put("mail.smtp.host", "smtp.gmail.com")
        props.put("mail.smtp.port", "587")
        val session: javax.mail.Session = javax.mail.Session.getInstance(props, object : Authenticator() {
            override fun getPasswordAuthentication(): javax.mail.PasswordAuthentication {
                return javax.mail.PasswordAuthentication(Credentials.EMAIL, Credentials.PASSWORD)
            }
        })
        val msg: Message = MimeMessage(session)
        msg.setFrom(InternetAddress(Credentials.EMAIL, false))
        val emailTo: String = extratoEmailDTO.email
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo))
        msg.setSubject("Data Transactions UserID: " + extratoEmailDTO.studentID)
        msg.setContent("Data Transactions...", "text/html")
        msg.setSentDate(Date())
        val messageBodyPart = MimeBodyPart()
        //val body: String = message.toString()
        messageBodyPart.setContent("This is an automatic email, please see attached your requested extract", "text/html")
        val multipart: Multipart = MimeMultipart()
        multipart.addBodyPart(messageBodyPart)
        val attachPart = MimeBodyPart()
        attachPart.attachFile(FilePath().filePath(extratoEmailDTO.studentID.toString()))
        multipart.addBodyPart(attachPart)
        msg.setContent(multipart)
        Transport.send(msg)
        println("Email Sent")
    }
}