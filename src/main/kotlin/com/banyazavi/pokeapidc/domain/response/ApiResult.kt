package com.banyazavi.pokeapidc.domain.response

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty

abstract class ApiResult(@JsonIgnore open val resultStatus: ResultStatus) {
    @get:JsonProperty("rc")
    val resultCode: Int
        get() {
            return resultStatus.resultCode
        }
}
