package com.cuncisboss.pokemontcg.datasource.set

import com.cuncisboss.pokemontcg.model.PokemonSet

interface PokemonSetDataStore {
    suspend fun getSets(): MutableList<PokemonSet>?
    suspend fun addAll(sets: MutableList<PokemonSet>?)
}