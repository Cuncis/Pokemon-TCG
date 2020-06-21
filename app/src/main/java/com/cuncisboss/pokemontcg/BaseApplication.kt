package com.cuncisboss.pokemontcg

import android.app.Application
import com.cuncisboss.pokemontcg.api.ApiClient
import com.cuncisboss.pokemontcg.datasource.pokemon.PokemonCardLocalDataStore
import com.cuncisboss.pokemontcg.datasource.pokemon.PokemonCardRemoteDataStore
import com.cuncisboss.pokemontcg.datasource.set.PokemonSetDataStore
import com.cuncisboss.pokemontcg.datasource.set.PokemonSetLocalDataStore
import com.cuncisboss.pokemontcg.datasource.set.PokemonSetRemoteDataSource
import com.cuncisboss.pokemontcg.db.AppDatabase
import com.cuncisboss.pokemontcg.db.PokemonCardRoomDataStore
import com.cuncisboss.pokemontcg.db.PokemonSetRoomDataStore
import com.cuncisboss.pokemontcg.repository.PokemonCardRepository
import com.cuncisboss.pokemontcg.repository.PokemonSetRepository

class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        val pokemonTcgService = ApiClient.POKEMON_TCG_SERVICE
        val appDatabase = AppDatabase.getInstance(this)

        PokemonSetRepository.instance.apply {
            init(
                PokemonSetRoomDataStore(appDatabase.pokemonSetDao()),
                PokemonSetRemoteDataSource(pokemonTcgService)
            )
        }

        PokemonCardRepository.instance.apply {
            init(
                PokemonCardRoomDataStore(appDatabase.pokemonCardDao()),
                PokemonCardRemoteDataStore(pokemonTcgService)
            )
        }

    }

}