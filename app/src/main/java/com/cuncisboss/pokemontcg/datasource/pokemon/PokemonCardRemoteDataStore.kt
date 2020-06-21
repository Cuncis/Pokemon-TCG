package com.cuncisboss.pokemontcg.datasource.pokemon

import com.cuncisboss.pokemontcg.api.PokemonTcgService
import com.cuncisboss.pokemontcg.model.PokemonCard
import java.lang.Exception

class PokemonCardRemoteDataStore(private val pokemonTcgService: PokemonTcgService): PokemonCardDataStore {

    override suspend fun getPokemons(set: String): MutableList<PokemonCard>? {
        val response = pokemonTcgService.getCards(set)
        if (response.isSuccessful) return response.body()?.cards

        throw Exception("Terjadi kesalahan saat melakukan request data, status error ${response.code()}")
    }

    override suspend fun addAll(set: String, pokemons: MutableList<PokemonCard>?) {
    }
}