package com.cuncisboss.pokemontcg.pokemonlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cuncisboss.pokemontcg.R
import com.cuncisboss.pokemontcg.model.PokemonCard
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_pokemon.*

class PokemonListAdapter: RecyclerView.Adapter<PokemonListAdapter.ViewHolder>() {

    private val pokemonCards = mutableListOf<PokemonCard>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        )

    override fun getItemCount(): Int = pokemonCards.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(pokemonCards[position])
    }

    fun updateData(newPokemonCard: MutableList<PokemonCard>) {
        pokemonCards.clear()
        pokemonCards.addAll(newPokemonCard)
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view), LayoutContainer {
        override val containerView: View?
            get() = itemView

        fun bindItem(pokemonCard: PokemonCard) {
            Glide.with(containerView!!)
                .load(pokemonCard.image)
                .into(ivCardLogo)

            tvCardName.text = pokemonCard.name
            tvCardRarity.text = pokemonCard.rarity

            containerView?.setOnClickListener {
                val action = PokemonListFragmentDirections.actionPokemonListFragmentToCardDetailFragment(
                    pokemonCard.name!!, pokemonCard
                )
                it.findNavController().navigate(action)
            }
        }

    }

}