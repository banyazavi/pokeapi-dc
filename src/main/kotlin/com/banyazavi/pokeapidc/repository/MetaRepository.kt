package com.banyazavi.pokeapidc.repository

import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import java.io.FileReader
import kotlin.reflect.KClass

abstract class MetaRepository<T : Any>(
    private val kClass: KClass<T>,
    private val path: String,
    private val csvSchema: CsvSchema
) {

    lateinit var metaMap: Map<String, T>
    lateinit var tempMetaMap: MutableMap<String, T>

    init {
        load()
        apply()
    }

    fun findById(id: String): T? {
        return metaMap[id]
    }

    fun existsById(id: String): Boolean {
        return metaMap.containsKey(id)
    }

    fun load() {
        val metaList = readMetaCsv()

        tempMetaMap = mutableMapOf()
        for (meta in metaList) {
            tempMetaMap[getPrimaryKey(meta)] = meta
        }
    }

    abstract fun getPrimaryKey(value: T): String

    fun apply() {
        metaMap = tempMetaMap.toMap()
    }

    private fun readMetaCsv(): List<T> {
        FileReader(path).use { reader ->
            return CsvMapper().registerModule(
                KotlinModule.Builder()
                    .withReflectionCacheSize(512)
                    .configure(KotlinFeature.NullToEmptyCollection, false)
                    .configure(KotlinFeature.NullToEmptyMap, false)
                    .configure(KotlinFeature.NullIsSameAsDefault, false)
                    .configure(KotlinFeature.SingletonSupport, false)
                    .configure(KotlinFeature.StrictNullChecks, false)
                    .build()
            )
                .readerFor(kClass.java)
                .with(csvSchema)
                .readValues<T>(reader)
                .readAll()
                .toList()
        }
    }
}
