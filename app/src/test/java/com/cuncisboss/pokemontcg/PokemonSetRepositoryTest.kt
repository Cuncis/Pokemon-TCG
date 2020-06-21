package com.cuncisboss.pokemontcg

import com.cuncisboss.pokemontcg.datasource.set.PokemonSetDataStore
import com.cuncisboss.pokemontcg.model.PokemonSet
import com.cuncisboss.pokemontcg.repository.PokemonSetRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import java.lang.Exception

class PokemonSetRepositoryTest {

    @Mock
    var localDataStore: PokemonSetDataStore? = null

    @Mock
    var remoteDataStore: PokemonSetDataStore? = null

    var pokemonSetRepository: PokemonSetRepository? = null

    var pokemonSets = mutableListOf<PokemonSet>()

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        pokemonSetRepository = PokemonSetRepository.instance.apply {
            init(localDataStore!!, remoteDataStore!!)
        }
    }

    @Test
    fun shouldNotGetPokemonsFromRemoteWhenLocalIsNotNull() {
        runBlocking {
            `when`(localDataStore?.getSets()).thenReturn(pokemonSets)
            pokemonSetRepository?.getSets()

            verify(remoteDataStore, never())?.getSets()
            verify(localDataStore, never())?.addAll(pokemonSets)
        }
    }

    @Test
    fun shouldCallGetPokemonsFromRemoteAndSaveToLocalWhenLocalIsNull() {
        runBlocking {
            `when`(localDataStore?.getSets()).thenReturn(null)
            `when`(remoteDataStore?.getSets()).thenReturn(pokemonSets)
            pokemonSetRepository?.getSets()

            verify(remoteDataStore, times(1))?.getSets()
            verify(localDataStore, times(1))?.addAll(pokemonSets)
        }
    }

    @Test
    fun shouldThrowExceptionWhenRemoteThrownAnException() {
        runBlocking {
            `when`(localDataStore?.getSets()).thenReturn(null)
            `when`(remoteDataStore?.getSets()).thenAnswer { throw Exception() }

            try {
                pokemonSetRepository?.getSets()
            } catch (e: Exception) {}
        }
    }

}