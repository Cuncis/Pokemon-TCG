package com.cuncisboss.pokemontcg.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.cuncisboss.pokemontcg.model.PokemonCard


@Dao
interface PokemonCardDao {

    @Query("SELECT * FROM PokemonCard WHERE `set` =:set")
    suspend fun getAll(set: String): MutableList<PokemonCard>

    @Query("DELETE FROM PokemonCard")
    suspend fun deleteAll()

    @Insert
    suspend fun insertAll(vararg pokemonCards: PokemonCard)

}