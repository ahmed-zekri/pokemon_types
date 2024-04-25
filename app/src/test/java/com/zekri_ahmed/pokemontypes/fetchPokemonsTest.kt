package com.zekri_ahmed.pokemontypes

import com.zekri_ahmed.pokemontypes.common.Resources
import com.zekri_ahmed.pokemontypes.data.dto.Pokemon
import com.zekri_ahmed.pokemontypes.data.dto.PokemonByTypeResults
import com.zekri_ahmed.pokemontypes.data.dto.PokemonData
import com.zekri_ahmed.pokemontypes.data.dto.PokemonInfo
import com.zekri_ahmed.pokemontypes.data.dto.Sprites
import com.zekri_ahmed.pokemontypes.domain.repositories.MainRepository
import com.zekri_ahmed.pokemontypes.domain.use_cases.FetchPokemonById
import com.zekri_ahmed.pokemontypes.domain.use_cases.FetchPokemonByType
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.anyString
import org.mockito.Mockito.mock
import retrofit2.Response

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class fetchPokemonsTest {
    private lateinit var repository: MainRepository
    private lateinit var fetchPokemonByType: FetchPokemonByType
    private lateinit var fetchPokemonById: FetchPokemonById

    @Before
    fun setUp() {
        repository = mock(MainRepository::class.java)
        fetchPokemonByType = FetchPokemonByType(repository)
        fetchPokemonById = FetchPokemonById(repository)
    }

    @Test
    fun `test if fetchPokemonByType returns success when api call succeeds`() = runBlocking {
        var calls = 0
        val pokemonData = listOf(PokemonData(Pokemon("ABC", ""), 1))
        Mockito.`when`(
            repository.getPokemonByType(anyString())
        ).thenReturn(
            Response.success(
                PokemonByTypeResults(
                    pokemonData

                )
            )
        )


        fetchPokemonByType(anyString()).collect {
            if (calls == 0)
                assert(it is Resources.Loading)
            else
                assert(it is Resources.Success && it.data == pokemonData.map { it.pokemon })
            calls++

        }


    }

    @Test
    fun `test if fetchPokemonByType returns Error when api call fails`() = runBlocking {
        var calls = 0
        listOf(PokemonData(Pokemon("ABC", ""), 1))
        Mockito.`when`(
            repository.getPokemonByType(anyString())
        ).thenReturn(
            Response.error(
                401,
                "{\"key\":[\"error\"]}"
                    .toResponseBody("application/json".toMediaTypeOrNull())
            )
        )


        fetchPokemonByType(anyString()).collect {
            if (calls == 0)
                assert(it is Resources.Loading)
            else
                assert(it is Resources.Error)
            calls++

        }


    }



    @Test
    fun `test if fetchPokemonById returns success when api call succeeds`() = runBlocking {
        var calls = 0
        Pokemon("ABC", "")
        val pokemonInfo = PokemonInfo("Abc", Sprites("","","",""))
        Mockito.`when`(
            repository.getPokemonById(anyString())
        ).thenReturn(
            Response.success(
                pokemonInfo
            )
        )
        fetchPokemonById(anyString()).collect {
            if (calls == 0)
                assert(it is Resources.Loading)
            else
                assert(it is Resources.Success && it.data == pokemonInfo)

            calls++

        }

    }

        @Test
    fun `test if fetchPokemonById returns Error when api call fails`() = runBlocking {
        var calls = 0
        listOf(PokemonData(Pokemon("ABC", ""), 1))
        Mockito.`when`(
            repository.getPokemonById(anyString())
        ).thenReturn(
            Response.error(
                401,
                "{\"key\":[\"error\"]}"
                    .toResponseBody("application/json".toMediaTypeOrNull())
            )
        )


        fetchPokemonById(anyString()).collect {
            if (calls == 0)
                assert(it is Resources.Loading)
            else
                assert(it is Resources.Error)
            calls++

        }


    }


}