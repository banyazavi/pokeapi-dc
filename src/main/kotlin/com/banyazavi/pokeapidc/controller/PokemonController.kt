package com.banyazavi.pokeapidc.controller

import com.banyazavi.pokeapidc.domain.request.ValidationQueryString
import com.banyazavi.pokeapidc.domain.response.ResultStatus
import com.banyazavi.pokeapidc.repository.PokemonRepository
import com.banyazavi.pokeapidc.service.ValidationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class PokemonController(
    private val validationService: ValidationService,
    private val pokemonRepository: PokemonRepository
) {

    @GetMapping("/pokemon/{pokemonId}")
    fun pokemon(
        @PathVariable pokemonId: String,
        @ModelAttribute queryString: ValidationQueryString
    ): ResponseEntity<String> {

        val resultStatus = validationService.validate("pokemon", pokemonId, queryString)
        if (resultStatus != ResultStatus.SUCCESS) {
            return ResponseEntity<String>(resultStatus.resultCode.toString(), resultStatus.httpStatus)
        }

        return ResponseEntity<String>(pokemonRepository.findById(pokemonId).toString(), HttpStatus.OK)
    }
}
