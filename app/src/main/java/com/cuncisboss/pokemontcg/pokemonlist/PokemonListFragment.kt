package com.cuncisboss.pokemontcg.pokemonlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.cuncisboss.pokemontcg.R
import com.cuncisboss.pokemontcg.model.PokemonCard
import com.cuncisboss.pokemontcg.repository.PokemonCardRepository
import kotlinx.android.synthetic.main.fragment_pokemon_list.*
import java.lang.Exception


class PokemonListFragment : Fragment(R.layout.fragment_pokemon_list) {

    private lateinit var pokemonListViewModel: PokemonListViewModel
    private lateinit var pokemonListAdapter: PokemonListAdapter

    private val args: PokemonListFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokemonListAdapter = PokemonListAdapter()
        rvCard.adapter = pokemonListAdapter

        val factory = PokemonListViewModelFactory(PokemonCardRepository.instance)
        pokemonListViewModel = ViewModelProvider(this, factory).get(PokemonListViewModel::class.java).apply {
            viewState.observe(
                viewLifecycleOwner,
                Observer(this@PokemonListFragment::handleState)
            )
            if (viewState.value?.data == null) getPokemons(args.setName)
            srlCard.setOnRefreshListener { getPokemons(args.setName) }
        }
    }

    private fun handleState(viewState: PokemonListViewState) {
        viewState.let {
            toggleLoading(it.loading)
            it.data?.let { data -> showData(data) }
            it.error?.let { error -> showError(error) }
        }
    }

    private fun showData(data: MutableList<PokemonCard>) {
        pokemonListAdapter.updateData(data)
        tvCardError.visibility = View.GONE
        rvCard.visibility = View.VISIBLE
    }

    private fun showError(error: Exception) {
        tvCardError.text = error.message
        tvCardError.visibility = View.VISIBLE
        rvCard.visibility = View.GONE
    }

    private fun toggleLoading(loading: Boolean) {
        srlCard.isRefreshing = loading
    }
}