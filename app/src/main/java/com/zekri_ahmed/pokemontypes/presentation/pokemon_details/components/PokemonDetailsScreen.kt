package com.zekri_ahmed.pokemontypes.presentation.pokemon_details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.zekri_ahmed.pokemontypes.presentation.pokemon_details.PokemonsDetailsViewModel


@Composable
fun PokemonDetails(
    navHostController: NavHostController,
    pokemonsDetailsViewModel: PokemonsDetailsViewModel = hiltViewModel()
) {


    Column(Modifier.fillMaxSize()) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 25.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = pokemonsDetailsViewModel.fetchPokemonByIdState.value?.name ?: "",
                fontFamily = FontFamily.Serif, fontSize = 25.sp
            )

        }

    }

}