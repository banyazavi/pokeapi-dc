package com.banyazavi.pokeapidc.service

import com.banyazavi.pokeapidc.domain.meta.Ability
import com.banyazavi.pokeapidc.domain.meta.Pokemon
import com.banyazavi.pokeapidc.domain.meta.Type
import com.banyazavi.pokeapidc.repository.*
import org.springframework.stereotype.Service

@Service
class PokemonService(
    private val bannedListRepository: BannedListRepository,
    private val pokemonRepository: PokemonRepository,
    private val pokemonTypeRepository: PokemonTypeRepository,
    private val typeRepository: TypeRepository,
    private val pokemonAbilityRepository: PokemonAbilityRepository,
    private val abilityRepository: AbilityRepository
) {

    fun isCensored(pokemonId: String): Boolean {
        return bannedListRepository.existsById(pokemonId)
    }

    fun getPokemon(pokemonId: String): Pokemon? {
        return pokemonRepository.findById(pokemonId)
    }

    fun getTypes(pokemonId: String): List<Type> {
        val types = mutableListOf<Type>()
        for (i in 1..2) {
            typeRepository.findById(
                pokemonTypeRepository.findById(
                    listOf(
                        pokemonId,
                        i.toString()
                    ).joinToString()
                )?.typeId.toString()
            )?.let { types.add(it) }
        }

        return types.toList()
    }

    fun getAbilities(pokemonId: String): List<Ability?> {
        val abilities = mutableListOf<Ability?>()
        for (i in 1..3) {
            abilities.add(
                abilityRepository.findById(
                    (pokemonAbilityRepository.findById(
                        listOf(
                            pokemonId,
                            i.toString()
                        ).joinToString()
                    )?.abilityId ?: -1).toString()
                )
            )
        }

        return abilities.toList()
    }
}