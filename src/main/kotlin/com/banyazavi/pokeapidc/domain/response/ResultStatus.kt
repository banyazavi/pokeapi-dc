package com.banyazavi.pokeapidc.domain.response

import org.springframework.http.HttpStatus

enum class ResultStatus(val httpStatus: HttpStatus, val resultCode: Int) {
    SUCCESS(HttpStatus.OK, 2000),
    CENSORED(HttpStatus.OK, 2001),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, 4000),
    INVALID_ID(HttpStatus.BAD_REQUEST, 4001),
    NULL_TIMESTAMP(HttpStatus.BAD_REQUEST, 4002),
    INVALID_TIMESTAMP(HttpStatus.BAD_REQUEST, 4003),
    NULL_CHECKSUM(HttpStatus.BAD_REQUEST, 4004),
    INVALID_CHECKSUM(HttpStatus.BAD_REQUEST, 4005),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 5000)
}
