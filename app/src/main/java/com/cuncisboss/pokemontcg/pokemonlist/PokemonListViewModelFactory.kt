package com.cuncisboss.pokemontcg.pokemonlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cuncisboss.pokemontcg.repository.PokemonCardRepository
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class PokemonListViewModelFactory(private val pokemonRepository: PokemonCardRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonListViewModel::class.java))
            return PokemonListViewModel(pokemonRepository) as T
        throw IllegalArgumentException()
    }
}