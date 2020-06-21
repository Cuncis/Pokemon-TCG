package com.cuncisboss.pokemontcg.setlist

import com.cuncisboss.pokemontcg.model.PokemonSet
import java.lang.Exception

data class SetListViewState(
    var loading: Boolean = false,
    var error: Exception? = null,
    var data: MutableList<PokemonSet>? = null
)