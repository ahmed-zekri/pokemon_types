package com.zekri_ahmed.pokemontypes.domain.repositories

import androidx.paging.PagingData
import com.zekri_ahmed.pokemontypes.data.dto.Pokemon
import com.zekri_ahmed.pokemontypes.data.dto.PokemonInfo
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MainRepository {

    fun getAllPokemons(): Flow<PagingData<Pokemon>>
    suspend fun getPokemonById(id: String): Response<PokemonInfo>


}