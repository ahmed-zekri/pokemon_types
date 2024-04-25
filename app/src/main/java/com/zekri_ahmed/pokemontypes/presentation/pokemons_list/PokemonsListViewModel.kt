package com.zekri_ahmed.pokemontypes.presentation.pokemons_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.zekri_ahmed.pokemontypes.data.dto.Pokemon
import com.zekri_ahmed.pokemontypes.data.dto.PokemonType
import com.zekri_ahmed.pokemontypes.domain.use_cases.FetchPokemonByType
import com.zekri_ahmed.pokemontypes.domain.use_cases.FetchPokemons
import com.zekri_ahmed.pokemontypes.domain.use_cases.FetchPokemonsTypes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonsListViewModel @Inject constructor(
    private val fetchPokemons: FetchPokemons,
    private val fetchPokemonsTypes: FetchPokemonsTypes,
    private val fetchPokemonByType: FetchPokemonByType
) :
    ViewModel() {

    private val _fetchAllPokemons = mutableStateOf<Flow<PagingData<Pokemon>>?>(null)
    val fetchAllPokemonsListState: State<Flow<PagingData<Pokemon>>?> = _fetchAllPokemons

    private val _fetchAllTypes = mutableStateOf<List<PokemonType>?>(null)
    val fetchAllPokemonsTypesState: State<List<PokemonType>?> = _fetchAllTypes

    private val _fetchByType = mutableStateOf<List<Pokemon>?>(null)
    val fetchPokemonsByTypeState: State<List<Pokemon>?> = _fetchByType


    init {
        getPagingFlow()
        getAllTypes()
    }

    private fun getAllTypes() {
        viewModelScope.launch {
            fetchPokemonsTypes().collect {
                _fetchAllTypes.value = it
            }
        }
    }

    fun getPokemonsByType(type: String) {

        viewModelScope.launch {
            fetchPokemonByType(type).collect {
                _fetchByType.value = it
            }
        }

    }

    private fun getPagingFlow() {
        _fetchAllPokemons.value = fetchPokemons()
    }


}