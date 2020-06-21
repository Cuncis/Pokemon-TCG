package com.cuncisboss.pokemontcg.datasource.pokemon

import com.cuncisboss.pokemontcg.model.PokemonCard

interface PokemonCardDataStore {
    suspend fun getPokemons(set: String): MutableList<PokemonCard>?
    suspend fun addAll(set: String, pokemons: MutableList<PokemonCard>?)
}