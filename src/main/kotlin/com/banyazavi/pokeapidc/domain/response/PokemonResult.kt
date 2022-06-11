package com.banyazavi.pokeapidc.domain.response

import com.banyazavi.pokeapidc.domain.meta.Ability
import com.banyazavi.pokeapidc.domain.meta.Pokemon
import com.banyazavi.pokeapidc.domain.meta.Type
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty

data class PokemonResult(
    @JsonIgnore override val resultStatus: ResultStatus,
    @JsonIgnore val pokemon: Pokemon,
    @JsonIgnore val types: List<Type>,
    @JsonIgnore val abilities: List<Ability?>
) : ApiResult(resultStatus) {
    @get:JsonProperty("id")
    val id: Int
        get() {
            return pokemon.id
        }

    @get:JsonProperty("name")
    val name: String
        get() {
            return pokemon.name
        }

    @get:JsonProperty("height")
    val height: Int
        get() {
            return pokemon.height
        }

    @get:JsonProperty("weight")
    val weight: Int
        get() {
            return pokemon.weight
        }

    @get:JsonProperty("types")
    val typesString: List<String>
        get() {
            return types.map { t -> t.name }
        }

    @get:JsonProperty("abilities")
    val abilitiesString: List<String?>
        get() {
            return abilities.map { t -> t?.name }
        }
}