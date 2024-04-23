package com.zekri_ahmed.pokemontypes.domain.use_cases

import com.zekri_ahmed.pokemontypes.domain.repositories.MainRepository
import javax.inject.Inject



class FetchPokemons @Inject constructor(val mainRepository: MainRepository) {

    operator fun invoke() =
        mainRepository.getAllPokemons()


}


