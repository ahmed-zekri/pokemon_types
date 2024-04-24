package com.zekri_ahmed.pokemontypes.presentation.pokemons_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.zekri_ahmed.pokemontypes.data.dto.Pokemon
import com.zekri_ahmed.pokemontypes.domain.use_cases.FetchPokemons
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PokemonsListViewModel @Inject constructor(private val fetchPokemons: FetchPokemons) :
    ViewModel() {

    private val _fetchAllPokemons = mutableStateOf<Flow<PagingData<Pokemon>>?>(null)
    val fetchAllPokemonsListState: State<Flow<PagingData<Pokemon>>?> = _fetchAllPokemons


    init {
        getPagingFlow()
    }

    private fun getPagingFlow() {

    _fetchAllPokemons.value=fetchPokemons()
    }


}