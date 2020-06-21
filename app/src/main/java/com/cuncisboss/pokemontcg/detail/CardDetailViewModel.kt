package com.cuncisboss.pokemontcg.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cuncisboss.pokemontcg.model.PokemonCard

class CardDetailViewModel : ViewModel() {
    private val mViewState = MutableLiveData<CardDetailViewState>().apply {
        value = CardDetailViewState(null)
    }
    val viewState: LiveData<CardDetailViewState>
        get() = mViewState

    fun setData(pokemonCard: PokemonCard) {
        mViewState.value = mViewState.value?.copy(data = pokemonCard)
    }
}