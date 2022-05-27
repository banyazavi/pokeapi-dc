package com.banyazavi.pokeapidc.repository

import com.banyazavi.pokeapidc.domain.meta.BannedList
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import org.springframework.stereotype.Repository

@Repository
class BannedListRepository : MetaRepository<BannedList>(
    BannedList::class,
    "meta/BANNED_LIST.csv",
    CsvSchema.builder()
        .addColumn("pokemonId", CsvSchema.ColumnType.NUMBER)
        .build()
) {

    override fun getPrimaryKey(value: BannedList): String {
        return value.pokemonId.toString()
    }
}
