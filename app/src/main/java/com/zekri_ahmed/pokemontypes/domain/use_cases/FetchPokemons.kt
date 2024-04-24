package com.zekri_ahmed.pokemontypes.domain.use_cases

import androidx.paging.PagingData
import com.zekri_ahmed.pokemontypes.data.dto.Pokemon
import com.zekri_ahmed.pokemontypes.domain.repositories.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class FetchPokemons @Inject constructor(val mainRepository: MainRepository) {

    operator fun invoke(): Flow<PagingData<Pokemon>> =
        mainRepository.getAllPokemons()


}


