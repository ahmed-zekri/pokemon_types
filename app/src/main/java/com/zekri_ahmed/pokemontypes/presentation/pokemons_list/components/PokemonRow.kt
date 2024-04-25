package com.zekri_ahmed.pokemontypes.presentation.pokemons_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.zekri_ahmed.pokemontypes.data.dto.Pokemon
import com.zekri_ahmed.pokemontypes.presentation.Screen

@Composable
fun PokemonRow(pokemon: Pokemon, navHostController: NavHostController) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(25.dp)
            .clickable {
                pokemon.url
                    .split("/")
                    .let { array ->
                        navHostController.navigate(
                            Screen.PokemonDetails(
                                array[array.size - 2]
                            ).route
                        )

                    }
            }
    ) {
        Text(
            text = "Pokemon",
            fontSize = 20.sp,
            fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(75.dp))
        Text(
            text = pokemon.name,
            fontSize = 20.sp,
            fontFamily = FontFamily.Monospace
        )
    }

}