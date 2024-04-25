package com.zekri_ahmed.pokemontypes.domain.use_cases

import android.util.Log
import com.zekri_ahmed.pokemontypes.data.dto.PokemonInfo
import com.zekri_ahmed.pokemontypes.domain.repositories.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class FetchPokemonById @Inject constructor(val mainRepository: MainRepository) {

    operator fun invoke(id: String): Flow<PokemonInfo> =
        flow {
            try {


                mainRepository.getPokemonById(id).body()?.let {
                    emit(it)

                }
            } catch (exception: Exception) {
                Log.e("tag", exception.message ?: "")

            }

        }


}


