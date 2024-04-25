package com.zekri_ahmed.pokemontypes.domain.use_cases

import android.util.Log
import com.zekri_ahmed.pokemontypes.data.dto.Pokemon
import com.zekri_ahmed.pokemontypes.domain.repositories.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class FetchPokemonByType @Inject constructor(private val mainRepository: MainRepository) {

    operator fun invoke(type: String): Flow<List<Pokemon>> =
        flow {
            try {


                mainRepository.getPokemonByType(type).body()?.let {
                    emit(it.pokemon.map { it.pokemon })

                }
            } catch (exception: Exception) {
                Log.e("tag", exception.message ?: "")

            }

        }


}


