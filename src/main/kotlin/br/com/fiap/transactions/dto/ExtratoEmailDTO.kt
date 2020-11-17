package br.com.fiap.transactions.dto

import java.math.BigInteger

data class ExtratoEmailDTO (
        var email: String,
        var studentID: BigInteger,
        var startDateAndTime: String?,
        var endDateAndTime: String?
) {
    constructor(extratoEmailDTO: ExtratoEmailDTO): this (
            email = extratoEmailDTO.email,
            studentID = extratoEmailDTO.studentID,
            startDateAndTime = extratoEmailDTO.startDateAndTime,
            endDateAndTime = extratoEmailDTO.endDateAndTime
    )
}