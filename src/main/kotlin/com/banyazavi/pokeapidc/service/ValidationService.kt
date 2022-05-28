package com.banyazavi.pokeapidc.service

import com.banyazavi.pokeapidc.domain.request.ValidationQueryString
import com.banyazavi.pokeapidc.domain.response.ResultStatus
import org.springframework.stereotype.Service
import java.security.MessageDigest
import java.util.*

@Service
class ValidationService {
    val md: MessageDigest = MessageDigest.getInstance("MD5")

    fun validate(
        endpoint: String,
        pokemonId: String,
        queryString: ValidationQueryString,
        currentTimestamp: Long = System.currentTimeMillis() / 1000
    ): ResultStatus {

        try {
            val requestTimestamp = queryString.ts?.toLong()
                ?: return ResultStatus.NULL_TIMESTAMP
            if (requestTimestamp !in currentTimestamp - 60..currentTimestamp) {
                return ResultStatus.INVALID_TIMESTAMP
            }
        } catch (e: NumberFormatException) {
            return ResultStatus.INVALID_TIMESTAMP
        }

        if (queryString.cks == null) {
            return ResultStatus.NULL_CHECKSUM
        }

        val plainText = pokemonId + queryString.ts + endpoint
        md.update(plainText.toByteArray())
        val checksum = HexFormat.of().formatHex(md.digest()).substring(0, 7)
        if (checksum != queryString.cks) {
            return ResultStatus.INVALID_CHECKSUM
        }

        // TODO: CENSORED and INVALID_ID

        return ResultStatus.SUCCESS
    }
}
