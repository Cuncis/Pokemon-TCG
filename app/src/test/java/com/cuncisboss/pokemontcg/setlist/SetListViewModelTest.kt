package com.cuncisboss.pokemontcg.setlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cuncisboss.pokemontcg.repository.PokemonSetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.lang.Exception

class SetListViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    var pokemonSetRepository: PokemonSetRepository? = null

    var setListViewModel: SetListViewModel? = null

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        setListViewModel = SetListViewModel(pokemonSetRepository!!)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun shouldLoadingWhenFirstInitialized() {
        val state = setListViewModel!!.viewState.value!!
        assertTrue(state.loading)
        assertNull(state.error)
        assertNull(state.data)
    }

    @Test
    fun shouldStopLoadingAndGiveDataWhenSuccess() {
        runBlocking {
            Mockito.`when`(pokemonSetRepository?.getSets()).thenReturn(mutableListOf())
            setListViewModel?.getSets()
            val state = setListViewModel!!.viewState.value!!
            assertFalse(state.loading)
            assertNull(state.error)
            assertNotNull(state.data)
        }
    }

    @Test
    fun shouldThrowErrorWhenRepositoryIsThrowingError() {
        runBlocking {
            Mockito.`when`(pokemonSetRepository?.getSets()).thenAnswer { throw Exception() }
            setListViewModel?.getSets()
            val state = setListViewModel!!.viewState.value!!
            assertFalse(state.loading)
            assertNotNull(state.error)
            assertNull(state.data)
        }
    }

}