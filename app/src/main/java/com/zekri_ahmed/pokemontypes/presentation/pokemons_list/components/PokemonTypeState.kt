package com.zekri_ahmed.pokemontypes.presentation.pokemons_list.components

import com.zekri_ahmed.pokemontypes.data.dto.PokemonType

data class PokemonTypeState(
    val loading: Boolean = false,
    val pokemonTypeData: List<PokemonType>? = null,
    val error: String = ""
)
