package com.banyazavi.pokeapidc.controller

import com.banyazavi.pokeapidc.domain.meta.Pokemon
import com.banyazavi.pokeapidc.repository.PokemonRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class PokemonController(private val pokemonRepository: PokemonRepository) {

    @GetMapping("/pokemon/{pokemonId}")
    fun pokemon(@PathVariable pokemonId: String): Pokemon? {
        return pokemonRepository.findById(pokemonId)
    }
}
