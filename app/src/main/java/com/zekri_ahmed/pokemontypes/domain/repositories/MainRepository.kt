package com.zekri_ahmed.pokemontypes.domain.repositories

import androidx.paging.PagingData
import com.zekri_ahmed.pokemontypes.data.dto.Pokemon
import com.zekri_ahmed.pokemontypes.data.dto.PokemonByTypeResults
import com.zekri_ahmed.pokemontypes.data.dto.PokemonInfo
import com.zekri_ahmed.pokemontypes.data.dto.PokemonTypeResults
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MainRepository {

    fun getAllPokemons(): Flow<PagingData<Pokemon>>
    suspend fun getPokemonById(id: String): Response<PokemonInfo>
    suspend fun getPokemonByType(type: String): Response<PokemonByTypeResults>
    suspend fun getPokemonTypes(): Response<PokemonTypeResults>


}