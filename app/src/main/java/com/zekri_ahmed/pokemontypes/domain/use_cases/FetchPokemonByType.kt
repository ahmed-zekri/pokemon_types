package com.zekri_ahmed.pokemontypes.domain.use_cases

import com.zekri_ahmed.pokemontypes.common.Resources
import com.zekri_ahmed.pokemontypes.data.dto.Pokemon
import com.zekri_ahmed.pokemontypes.domain.repositories.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class FetchPokemonByType @Inject constructor(private val mainRepository: MainRepository) {

    operator fun invoke(type: String): Flow<Resources<List<Pokemon>>> =
        flow {
            try {

                emit(Resources.Loading())
                mainRepository.getPokemonByType(type).body()?.let {
                    emit(Resources.Success(it.pokemon.map { it.pokemon }))

                }
            } catch (exception: Exception) {
                emit(Resources.Error(exception.message ?: ""))
            }

        }


}


