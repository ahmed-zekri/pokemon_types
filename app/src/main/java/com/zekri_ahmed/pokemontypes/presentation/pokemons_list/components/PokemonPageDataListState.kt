package com.zekri_ahmed.pokemontypes.presentation.pokemons_list.components

import androidx.paging.PagingData
import com.zekri_ahmed.pokemontypes.data.dto.Pokemon
import kotlinx.coroutines.flow.Flow

data class PokemonPageDataListState(
    val loading: Boolean = false,
    val pokemonListData: Flow<PagingData<Pokemon>>? = null,
    val error: String = ""
)
