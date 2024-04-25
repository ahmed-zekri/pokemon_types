package com.zekri_ahmed.pokemontypes.presentation.pokemons_list.components

import com.zekri_ahmed.pokemontypes.data.dto.Pokemon

data class PokemonPerTypeState(
    val loading: Boolean = false,
    val pokemonPerTypeData: List<Pokemon>? = null,
    val error: String = ""
)
