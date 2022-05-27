package com.banyazavi.pokeapidc.repository

import com.banyazavi.pokeapidc.domain.meta.Type
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import org.springframework.stereotype.Repository

@Repository
class TypeRepository : MetaRepository<Type>(
    Type::class,
    "meta/TYPES.csv",
    CsvSchema.builder()
        .addColumn("id", CsvSchema.ColumnType.NUMBER)
        .addColumn("identifier", CsvSchema.ColumnType.STRING)
        .addColumn("name", CsvSchema.ColumnType.STRING)
        .build()
) {

    override fun getPrimaryKey(value: Type): String {
        return value.id.toString()
    }
}
