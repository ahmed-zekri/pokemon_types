package com.zekri_ahmed.pokemontypes.domain.use_cases

import com.zekri_ahmed.pokemontypes.common.Resources
import com.zekri_ahmed.pokemontypes.data.dto.PokemonType
import com.zekri_ahmed.pokemontypes.domain.repositories.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class FetchPokemonsTypes @Inject constructor(private val mainRepository: MainRepository) {

    operator fun invoke(): Flow<Resources<List<PokemonType>>> =
        flow {
            mainRepository.getPokemonTypes().body()?.let { response ->
                try {

                    emit(Resources.Loading())
                    emit(Resources.Success(response.results))
                } catch (exception: Exception) {


                    emit(Resources.Error(exception.message ?: ""))
                }

            }

        }


}


