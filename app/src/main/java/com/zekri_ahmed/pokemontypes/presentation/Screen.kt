package com.zekri_ahmed.pokemontypes.presentation

sealed class Screen(val route: String) {
    object PokemonsList : Screen(route = "pokemonsList")
    object PokemonDetails : Screen(route = "pokemonDetails")

}