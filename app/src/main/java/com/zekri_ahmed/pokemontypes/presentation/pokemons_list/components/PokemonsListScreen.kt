package com.zekri_ahmed.pokemontypes.presentation.pokemons_list.components

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.zekri_ahmed.pokemontypes.data.common.itemsList
import com.zekri_ahmed.pokemontypes.data.common.pagingLoadStateItem
import com.zekri_ahmed.pokemontypes.presentation.Screen
import com.zekri_ahmed.pokemontypes.presentation.pokemons_list.PokemonsListViewModel


@Composable
fun ItemsList(
    navHostController: NavHostController,
    pokemonsListViewModel: PokemonsListViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val pokemonsType by remember {
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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.TopEnd)
            ) {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More"
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("AllTypes") },
                        onClick = { Toast.makeText(context, "Load", Toast.LENGTH_SHORT).show() }
                    )
                    pokemonsListViewModel.fetchAllPokemonsTypesState.value?.let { pokemonTypes ->
                        pokemonTypes.forEach {
                            DropdownMenuItem(
                                text = { Text(it.name) },
                                onClick = {
                                    Toast.makeText(context, "Save", Toast.LENGTH_SHORT).show()
                                }
                            )
                        }
                    }

                }


            }

            items.value?.collectAsLazyPagingItems()?.let { lazyPagingItems ->
                LazyColumn {

                    itemsList(lazyPagingItems) { item ->

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    item?.url
                                        ?.split("/")
                                        ?.let { array ->
                                            navHostController.navigate(Screen.PokemonDetails(array[array.size - 2]).route)

                                        }
                                }
                                .height(50.dp)


                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center

                            ) {
                                Text(
                                    buildAnnotatedString {
                                        append(
                                            "Pokemon : ${
                                                item?.url?.split("/")
                                                    ?.get(item.url.split("/").size - 2)
                                            }"
                                        )
                                        withStyle(style = SpanStyle(fontWeight = FontWeight.W900)) {
                                            append(item?.name)
                                        }

                                    }
                                )
                            }


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


        }

    }

}