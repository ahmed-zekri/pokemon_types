package com.zekri_ahmed.pokemontypes.domain.repositories

import androidx.paging.PagingData
import com.zekri_ahmed.pokemontypes.data.dto.Pokemon
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun getAllPokemons(): Flow<PagingData<Pokemon>>


}