package com.cuncisboss.pokemontcg.pokemonlist

import com.cuncisboss.pokemontcg.model.PokemonCard
import com.cuncisboss.pokemontcg.model.PokemonSet
import java.lang.Exception

data class PokemonListViewState(
    var loading: Boolean = false,
    var error: Exception? = null,
    var data: MutableList<PokemonCard>? = null
)