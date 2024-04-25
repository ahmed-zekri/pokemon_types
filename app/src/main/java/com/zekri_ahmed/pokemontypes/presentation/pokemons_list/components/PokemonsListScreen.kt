package com.zekri_ahmed.pokemontypes.presentation.pokemons_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.zekri_ahmed.pokemontypes.data.common.itemsList
import com.zekri_ahmed.pokemontypes.data.common.pagingLoadStateItem
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
    val items = remember {
        mutableStateOf(
            pokemonsListViewModel.fetchAllPokemonsListState.value
        )
    }





    Column(Modifier.fillMaxSize()) {


        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {

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
                    pokemonsListViewModel.fetchAllPokemonsTypesState.value?.let { pokemonTypes ->
                        pokemonTypes.forEach {
                            DropdownMenuItem(text = {
                                Text(
                                    text = it.name,
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold
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
            if (pokemonsType.value == "AllTypes") items.value?.collectAsLazyPagingItems()
                ?.let { lazyPagingItems ->
                    LazyColumn {

                        itemsList(lazyPagingItems) { item ->
                            item?.let {
                                PokemonRow(it, navHostController)
                            }


                        }

                        pagingLoadStateItem(
                            loadState = lazyPagingItems.loadState.append,
                            keySuffix = "append",
                            loading = {
                                Column(modifier = Modifier.fillMaxWidth()) {
                                    CircularProgressIndicator(Modifier.align(Alignment.CenterHorizontally))
                                }
                            },
                            error = {

                            },
                        )
                    }


                }
            else LazyColumn {
                pokemonsListViewModel.getPokemonsByType(pokemonsType.value)
                pokemonsListViewModel.fetchPokemonsByTypeState.value?.let { list ->
                    items(list.size) {
                        PokemonRow(pokemon = list[it], navHostController)


                    }


                }

            }


        }

    }

}