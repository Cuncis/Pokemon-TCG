package com.cuncisboss.pokemontcg.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.cuncisboss.pokemontcg.R
import com.cuncisboss.pokemontcg.model.PokemonCard
import kotlinx.android.synthetic.main.fragment_card_detail.*


class CardDetailFragment : Fragment(R.layout.fragment_card_detail) {

    private lateinit var detailViewModel: CardDetailViewModel

    private val args: CardDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pokemonCard = args.pokemonCard
        detailViewModel = ViewModelProvider(this).get(CardDetailViewModel::class.java).apply {
            viewState.observe(
                viewLifecycleOwner,
                Observer(this@CardDetailFragment::handleState)
            )
            pokemonCard?.let { setData(it) }
        }
    }

    private fun handleState(viewState: CardDetailViewState) {
        viewState.data?.let { showDetail(it) }
    }

    private fun showDetail(data: PokemonCard) {
        Glide.with(this)
            .load(data.image)
            .into(ivLogo)

        tvName.text = data.name
        tvRarity.text = data.rarity
        tvSeries.text = data.series
    }

}