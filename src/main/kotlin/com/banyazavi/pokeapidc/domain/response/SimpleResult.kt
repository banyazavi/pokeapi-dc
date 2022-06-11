package com.banyazavi.pokeapidc.domain.response

import com.fasterxml.jackson.annotation.JsonIgnore

data class SimpleResult(@JsonIgnore override val resultStatus: ResultStatus) : ApiResult(resultStatus)
