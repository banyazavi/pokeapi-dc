package com.banyazavi.pokeapidc.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class CommonApiController {

    @GetMapping("/health-check")
    fun healthCheck() {
    }

    @RequestMapping("*")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun notFound() {
    }
}
