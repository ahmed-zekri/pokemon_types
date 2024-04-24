package com.zekri_ahmed.pokemontypes.presentation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zekri_ahmed.pokemontypes.presentation.pokemons_list.components.ItemsList
import com.zekri_ahmed.pokemontypes.presentation.pokemon_details.components.PokemonDetails

@Composable
fun Navigation() {
    val navController: NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.PokemonsList.route) {

        composable(route = Screen.PokemonsList.route) {
            ItemsList(navController)

        }

        composable(route = Screen.PokemonDetails.route) {
            PokemonDetails(navController)

        }


    }


}