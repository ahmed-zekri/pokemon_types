package com.zekri_ahmed.pokemontypes.presentation.pokemon_details.components

import com.zekri_ahmed.pokemontypes.data.dto.PokemonInfo

data class PokemonPerIdState(
    val loading: Boolean = false,
    val pokemonPerIdData: PokemonInfo? = null,
    val error: String = ""
)
