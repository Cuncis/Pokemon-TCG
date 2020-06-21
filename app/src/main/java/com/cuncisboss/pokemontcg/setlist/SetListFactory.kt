package com.cuncisboss.pokemontcg.setlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cuncisboss.pokemontcg.repository.PokemonSetRepository
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class SetListFactory(private val setRepository: PokemonSetRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SetListViewModel::class.java))
            return SetListViewModel(setRepository) as T
        throw IllegalArgumentException()
    }
}