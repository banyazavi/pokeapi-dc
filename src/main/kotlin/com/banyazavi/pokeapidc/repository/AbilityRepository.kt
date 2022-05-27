package com.banyazavi.pokeapidc.repository

import com.banyazavi.pokeapidc.domain.meta.Ability
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import org.springframework.stereotype.Repository

@Repository
class AbilityRepository : MetaRepository<Ability>(
    Ability::class,
    "meta/ABILITIES.csv",
    CsvSchema.builder()
        .addColumn("id", CsvSchema.ColumnType.NUMBER)
        .addColumn("identifier", CsvSchema.ColumnType.STRING)
        .addColumn("name", CsvSchema.ColumnType.STRING)
        .build()
) {

    override fun getPrimaryKey(value: Ability): String {
        return value.id.toString()
    }
}
