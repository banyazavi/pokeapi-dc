package com.banyazavi.pokeapidc.domain.meta

data class Pokemon(
    val id: Int,
    val identifier: String,
    val name: String,
    val height: Int,
    val weight: Int,
    val baseExperience: Int
)
