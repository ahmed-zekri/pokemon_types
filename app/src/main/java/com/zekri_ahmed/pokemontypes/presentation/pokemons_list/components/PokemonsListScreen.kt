package com.zekri_ahmed.pokemontypes.presentation.pokemons_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.zekri_ahmed.pokemontypes.presentation.pokemons_list.PokemonsListViewModel


@Composable
fun ItemsList(
    navHostController: NavHostController,
    pokemonsListViewModel: PokemonsListViewModel = hiltViewModel()
) {
    val pokemonsType = remember {
        mutableStateOf("AllTypes")
    }
    var expanded by remember { mutableStateOf(false) }


    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {


        Column(Modifier.fillMaxSize()) {
            //Get all pokemons types
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = pokemonsType.value,
                    modifier = Modifier.padding(start = 15.dp),
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    fontSize = 20.sp
                )
                IconButton(onClick = { expanded = !expanded }) {

                    Icon(
                        imageVector = Icons.Default.MoreVert, contentDescription = "More"
                    )
                }
                DropdownMenu(expanded = expanded,
                    modifier = Modifier.fillMaxWidth(),
                    onDismissRequest = { expanded = false }) {
                    DropdownMenuItem(text = {
                        Text(
                            text = "AllTypes",
                            fontSize = 20.sp,
                            fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(75.dp))
                    }, onClick = {
                        pokemonsType.value = "AllTypes"
                        expanded = false
                    })
                    pokemonsListViewModel.fetchAllPokemonsTypesState.value.pokemonTypeData?.let { pokemonTypes ->
                        pokemonTypes.forEach {
                            DropdownMenuItem(text = {
                                Text(
                                    text = it.name,
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.width(75.dp))
                            }, onClick = {
                                pokemonsType.value = it.name
                                expanded = false
                            })
                        }
                    }

                }


            }


            // Fetch All Pokemons
            pokemonsListViewModel.fetchAllPokemonsListState.value.pokemonListData?.let { items ->

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {


                    if (pokemonsType.value == "AllTypes") items.collectAsLazyPagingItems()
                        .let { lazyPagingItems ->
                            LazyColumn {

                                items(lazyPagingItems.itemCount) { index ->
                                    PokemonRow(
                                        pokemon = lazyPagingItems[index]!!,
                                        navHostController = navHostController
                                    )
                                }

                                lazyPagingItems.apply {
                                    when {
                                        loadState.refresh is LoadState.Loading -> {
                                            item {
                                                CircularProgressIndicator(
                                                    modifier = Modifier
                                                        .fillParentMaxSize()
                                                        .size(50.dp)
                                                )
                                            }
                                        }

                                        loadState.refresh is LoadState.Error -> {
                                            val error =
                                                lazyPagingItems.loadState.refresh as LoadState.Error
                                            item {
                                                Row(
                                                    modifier = Modifier.fillMaxSize(),
                                                    horizontalArrangement = Center
                                                ) {

                                                }
                                                ErrorMessageLoading(error = error.error.localizedMessage) {
                                                    retry()
                                                }
                                            }
                                        }


                                        loadState.append is LoadState.Loading -> {
                                            item {
                                                CircularProgressIndicator(
                                                    modifier = Modifier
                                                        .align(
                                                            Alignment.CenterHorizontally
                                                        )
                                                        .size(50.dp)
                                                )
                                            }
                                        }

                                        loadState.append is LoadState.Error -> {
                                            val error =
                                                lazyPagingItems.loadState.append as LoadState.Error
                                            item {
                                                ErrorMessageLoading(error = error.error.localizedMessage) {
                                                    retry()
                                                }
                                            }
                                        }
                                    }
                                }
                            }


                        }
                    else
                    // Fetch Pokemon Per type
                    {
                        LaunchedEffect(key1 = pokemonsType.value) {
                            pokemonsListViewModel.getPokemonsByType(pokemonsType.value)

                        }
                        LazyColumn {

                            pokemonsListViewModel.fetchPokemonsByTypeState.value.pokemonPerTypeData?.let { list ->
                                items(list.size) {
                                    PokemonRow(pokemon = list[it], navHostController)


                                }
                            }

                        }

                    }

                }
            }

        }
//Loading screen
        if (pokemonsListViewModel.fetchAllPokemonsListState.value.loading)
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(50.dp),
                color = Color.Black
            )


    }
}