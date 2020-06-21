package com.cuncisboss.pokemontcg.setlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cuncisboss.pokemontcg.R
import com.cuncisboss.pokemontcg.model.PokemonSet
import com.cuncisboss.pokemontcg.repository.PokemonSetRepository
import kotlinx.android.synthetic.main.fragment_set_list.*
import java.lang.Exception


class SetListFragment : Fragment(R.layout.fragment_set_list) {

    private lateinit var setListViewModel: SetListViewModel
    private lateinit var setListAdapter: SetListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListAdapter = SetListAdapter()
        rvSet.adapter = setListAdapter

        val factory = SetListFactory(PokemonSetRepository.instance)
        setListViewModel = ViewModelProvider(this, factory).get(SetListViewModel::class.java).apply {
            viewState.observe(viewLifecycleOwner, Observer(this@SetListFragment::handleState))
            srlSet.setOnRefreshListener { getSets() }
        }
    }

    private fun handleState(viewState: SetListViewState) {
        viewState.let {
            toggleLoading(it.loading)
            it.data?.let { data -> showData(data) }
            it.error?.let { error -> showError(error) }
        }
    }

    private fun showError(error: Exception) {
        tvSetError.text = error.message
        tvSetError.visibility = View.VISIBLE
        rvSet.visibility = View.GONE
    }

    private fun showData(data: MutableList<PokemonSet>) {
        setListAdapter.updateData(data)
        tvSetError.visibility = View.GONE
        rvSet.visibility = View.VISIBLE
    }

    private fun toggleLoading(loading:Boolean) {
        srlSet.isRefreshing = loading
    }

}