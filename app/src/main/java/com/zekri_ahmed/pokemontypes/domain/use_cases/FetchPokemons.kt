package com.zekri_ahmed.pokemontypes.domain.use_cases

import com.zekri_ahmed.pokemontypes.common.Resources
import com.zekri_ahmed.pokemontypes.domain.repositories.MainRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class FetchPokemons @Inject constructor(private val mainRepository: MainRepository) {

    operator fun invoke() =
        flow {
            try {

                emit(Resources.Loading())
                emit(Resources.Success(mainRepository.getAllPokemons()))
            } catch (e: Exception) {

                emit(Resources.Error(e.message ?: ""))

            }
        }
}


