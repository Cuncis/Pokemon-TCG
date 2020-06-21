package com.cuncisboss.pokemontcg.api

import com.cuncisboss.pokemontcg.model.PokemonCard
import com.cuncisboss.pokemontcg.model.PokemonSet
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object ApiClient {
    private const val BASE_URL = "https://api.pokemontcg.io/v1/"

    private val client = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val POKEMON_TCG_SERVICE: PokemonTcgService = client.create(
        PokemonTcgService::class.java)
}