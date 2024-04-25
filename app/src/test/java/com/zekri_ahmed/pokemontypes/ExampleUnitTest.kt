package com.zekri_ahmed.pokemontypes

import com.zekri_ahmed.pokemontypes.domain.repositories.MainRepository
import com.zekri_ahmed.pokemontypes.domain.use_cases.FetchPokemons
import org.junit.Before
import org.mockito.Mockito.mock

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    private lateinit var repository: MainRepository
    private lateinit var fetchPokemons: FetchPokemons

    @Before
    fun setUp() {
        repository = mock(MainRepository::class.java)
        fetchPokemons = FetchPokemons(repository)

    }
    fun `test if fetchWeather method by cityName throws correct exception when a non String type or an empty String are received as input`

}