package com.banyazavi.pokeapidc.repository

import com.banyazavi.pokeapidc.domain.meta.PokemonAbility
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import org.springframework.stereotype.Repository

@Repository
class PokemonAbilityRepository : MetaRepository<PokemonAbility>(
    PokemonAbility::class,
    "meta/POKEMON_ABILITIES.csv",
    CsvSchema.builder()
        .addColumn("pokemonId", CsvSchema.ColumnType.NUMBER)
        .addColumn("abilityId", CsvSchema.ColumnType.NUMBER)
        .addColumn("slot", CsvSchema.ColumnType.NUMBER)
        .build()
) {

    override fun getPrimaryKey(value: PokemonAbility): String {
        return listOf(value.pokemonId.toString(), value.slot.toString()).joinToString()
    }
}
