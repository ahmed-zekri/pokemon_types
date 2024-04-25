package com.zekri_ahmed.pokemontypes.domain.use_cases

import android.util.Log
import com.zekri_ahmed.pokemontypes.data.dto.PokemonType
import com.zekri_ahmed.pokemontypes.domain.repositories.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class FetchPokemonsTypes @Inject constructor(private val mainRepository: MainRepository) {

    operator fun invoke(): Flow<List<PokemonType>> =
        flow {
            mainRepository.getPokemonTypes().body()?.let { response ->
                try {


                    emit(response.results)
                } catch (exception: Exception) {

                    Log.e("tag", exception.message ?: "")
                }

            }

        }


}


