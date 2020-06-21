package com.cuncisboss.pokemontcg.db

import com.cuncisboss.pokemontcg.datasource.set.PokemonSetDataStore
import com.cuncisboss.pokemontcg.model.PokemonSet

class PokemonSetRoomDataStore(private val pokemonSetDao: PokemonSetDao): PokemonSetDataStore {
    override suspend fun getSets(): MutableList<PokemonSet>? {
        val response = pokemonSetDao.getAll()
        return if (response.isEmpty()) null else response
    }

    override suspend fun addAll(sets: MutableList<PokemonSet>?) {
        sets?.let { pokemonSetDao.insertAll() }
    }
}