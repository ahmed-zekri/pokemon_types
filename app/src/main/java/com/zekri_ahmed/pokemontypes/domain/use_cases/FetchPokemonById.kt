package com.zekri_ahmed.pokemontypes.domain.use_cases

import com.zekri_ahmed.pokemontypes.common.Resources
import com.zekri_ahmed.pokemontypes.data.dto.PokemonInfo
import com.zekri_ahmed.pokemontypes.domain.repositories.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class FetchPokemonById @Inject constructor(private val mainRepository: MainRepository) {

    operator fun invoke(id: String): Flow<Resources<PokemonInfo>> =
        flow {
            try {
                emit(Resources.Loading())

                mainRepository.getPokemonById(id).body()?.let {
                    emit(Resources.Success(it))

                }
            } catch (exception: Exception) {
                emit(Resources.Error(exception.message ?: ""))
            }

        }


}


