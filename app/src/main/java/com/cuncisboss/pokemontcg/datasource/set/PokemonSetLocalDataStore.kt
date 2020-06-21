package com.cuncisboss.pokemontcg.datasource.set

import com.cuncisboss.pokemontcg.model.PokemonSet

class PokemonSetLocalDataStore:
    PokemonSetDataStore {

    private var caches = mutableListOf<PokemonSet>()

    override suspend fun getSets(): MutableList<PokemonSet>? =
        if (caches.isNotEmpty()) caches else null

    override suspend fun addAll(sets: MutableList<PokemonSet>?) {
        sets?.let {
            caches = it
        }
    }
}