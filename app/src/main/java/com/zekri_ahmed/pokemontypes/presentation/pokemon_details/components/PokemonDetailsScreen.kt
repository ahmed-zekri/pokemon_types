package com.zekri_ahmed.pokemontypes.presentation.pokemon_details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
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
            pokemonsDetailsViewModel.fetchPokemonByIdState.value?.sprites?.frontDefault
                ?.let { url ->
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(minSize = 150.dp)
                    ) {
                        items(4) {
                            when (it) {
                                0 -> pokemonsDetailsViewModel.fetchPokemonByIdState.value?.sprites?.backDefault
                                1 -> pokemonsDetailsViewModel.fetchPokemonByIdState.value?.sprites?.frontDefault
                                2 -> pokemonsDetailsViewModel.fetchPokemonByIdState.value?.sprites?.backShiny
                                else -> pokemonsDetailsViewModel.fetchPokemonByIdState.value?.sprites?.frontShiny

                            }?.let { url ->
                                AsyncImage(
                                    modifier = Modifier.height(250.dp),
                                    model = url,
                                    contentDescription = url
                                )
                            }


                        }

                    }


                }


        }

    }

}