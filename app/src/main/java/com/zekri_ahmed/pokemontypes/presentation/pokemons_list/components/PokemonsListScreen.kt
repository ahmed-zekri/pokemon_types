package com.zekri_ahmed.pokemontypes.presentation.pokemons_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.zekri_ahmed.pokemontypes.data.common.itemsList
import com.zekri_ahmed.pokemontypes.data.common.pagingLoadStateItem
import com.zekri_ahmed.pokemontypes.presentation.InputTextField
import com.zekri_ahmed.pokemontypes.presentation.Screen
import com.zekri_ahmed.pokemontypes.presentation.pokemons_list.PokemonsListViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


@Composable
fun ItemsList(
    navHostController: NavHostController,
    pokemonsListViewModel: PokemonsListViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val hotTypingFlow = remember {
        MutableStateFlow("")
    }
    val items = remember {
        mutableStateOf(
            pokemonsListViewModel.fetchAllPokemonsListState.value
        )
    }





    Column(Modifier.fillMaxSize()) {


        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            InputTextField(
                spacer = 2.dp,
                labelText = "",
                dividerColor = Color.Black,
                textStyle = TextStyle.Default

            ) {
                coroutineScope.launch {
                    if (this@InputTextField.isNotBlank())
                        hotTypingFlow.emit(this@InputTextField)
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