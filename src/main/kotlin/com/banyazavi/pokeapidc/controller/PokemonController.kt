package com.banyazavi.pokeapidc.controller

import com.banyazavi.pokeapidc.domain.meta.Ability
import com.banyazavi.pokeapidc.domain.meta.Pokemon
import com.banyazavi.pokeapidc.domain.meta.Type
import com.banyazavi.pokeapidc.domain.request.ValidationQueryString
import com.banyazavi.pokeapidc.domain.response.ApiResult
import com.banyazavi.pokeapidc.domain.response.PokemonResult
import com.banyazavi.pokeapidc.domain.response.ResultStatus
import com.banyazavi.pokeapidc.domain.response.SimpleResult
import com.banyazavi.pokeapidc.service.PokemonService
import com.banyazavi.pokeapidc.service.ValidationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class PokemonController(
    private val validationService: ValidationService,
    private val pokemonService: PokemonService
) {

    @GetMapping("/pokemon/{pokemonId}")
    fun pokemon(
        @PathVariable pokemonId: String,
        @ModelAttribute queryString: ValidationQueryString
    ): ResponseEntity<ApiResult> {

        var resultStatus = validationService.validate("pokemon", pokemonId, queryString)
        if (resultStatus != ResultStatus.SUCCESS) {
            return ResponseEntity<ApiResult>(SimpleResult(resultStatus), resultStatus.httpStatus)
        }

        val pokemon: Pokemon? = pokemonService.getPokemon(pokemonId)

        if (pokemon == null) {
            resultStatus = ResultStatus.INVALID_ID
            return ResponseEntity<ApiResult>(SimpleResult(resultStatus), resultStatus.httpStatus)
        }

        if (pokemonService.isCensored(pokemonId)) {
            resultStatus = ResultStatus.CENSORED
            return ResponseEntity<ApiResult>(SimpleResult(resultStatus), resultStatus.httpStatus)
        }

        val types: List<Type> = pokemonService.getTypes(pokemonId)
        val abilities: List<Ability?> = pokemonService.getAbilities(pokemonId)

        return ResponseEntity<ApiResult>(
            PokemonResult(resultStatus, pokemon, types, abilities),
            resultStatus.httpStatus
        )
    }
}
