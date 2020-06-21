package com.cuncisboss.pokemontcg.datasource.set

import com.cuncisboss.pokemontcg.api.PokemonTcgService
import com.cuncisboss.pokemontcg.model.PokemonSet
import java.lang.Exception

class PokemonSetRemoteDataSource(private val pokemonTcgService: PokemonTcgService):
    PokemonSetDataStore {

    override suspend fun getSets(): MutableList<PokemonSet>? {
        val response = pokemonTcgService.getSets()
        if (response.isSuccessful) return response.body()?.sets

        throw Exception("Terjadi kesalahan saat melakukan request data, status error ${response.code()}")
    }

    override suspend fun addAll(sets: MutableList<PokemonSet>?) {
        TODO("Not yet implemented")
    }
}