package com.banyazavi.pokeapidc.repository

import com.banyazavi.pokeapidc.domain.meta.Pokemon
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import org.springframework.stereotype.Repository

@Repository
class PokemonRepository : MetaRepository<Pokemon>(
    Pokemon::class,
    "meta/POKEMON.csv",
    CsvSchema.builder()
        .addColumn("id", CsvSchema.ColumnType.NUMBER)
        .addColumn("identifier", CsvSchema.ColumnType.STRING)
        .addColumn("name", CsvSchema.ColumnType.STRING)
        .addColumn("height", CsvSchema.ColumnType.NUMBER)
        .addColumn("weight", CsvSchema.ColumnType.NUMBER)
        .addColumn("baseExperience", CsvSchema.ColumnType.NUMBER)
        .build()
) {

    override fun getPrimaryKey(value: Pokemon): String {
        return value.id.toString()
    }
}
