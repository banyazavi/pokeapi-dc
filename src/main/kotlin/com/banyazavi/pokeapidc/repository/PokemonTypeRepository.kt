package com.banyazavi.pokeapidc.repository

import com.banyazavi.pokeapidc.domain.meta.PokemonType
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import org.springframework.stereotype.Repository

@Repository
class PokemonTypeRepository : MetaRepository<PokemonType>(
    PokemonType::class,
    "meta/POKEMON_TYPES.csv",
    CsvSchema.builder()
        .addColumn("pokemonId", CsvSchema.ColumnType.NUMBER)
        .addColumn("typeId", CsvSchema.ColumnType.NUMBER)
        .addColumn("slot", CsvSchema.ColumnType.NUMBER)
        .build()
) {

    override fun getPrimaryKey(value: PokemonType): String {
        return listOf(value.pokemonId.toString(), value.slot.toString()).joinToString()
    }
}
