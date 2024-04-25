package com.zekri_ahmed.pokemontypes.presentation.pokemons_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zekri_ahmed.pokemontypes.common.Resources
import com.zekri_ahmed.pokemontypes.domain.use_cases.FetchPokemonByType
import com.zekri_ahmed.pokemontypes.domain.use_cases.FetchPokemons
import com.zekri_ahmed.pokemontypes.domain.use_cases.FetchPokemonsTypes
import com.zekri_ahmed.pokemontypes.presentation.pokemons_list.components.PokemonPageDataListState
import com.zekri_ahmed.pokemontypes.presentation.pokemons_list.components.PokemonPerTypeState
import com.zekri_ahmed.pokemontypes.presentation.pokemons_list.components.PokemonTypeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PokemonsListViewModel @Inject constructor(
    private val fetchPokemons: FetchPokemons,
    private val fetchPokemonsTypes: FetchPokemonsTypes,
    private val fetchPokemonByType: FetchPokemonByType
) :
    ViewModel() {

    private val _fetchAllPokemons = mutableStateOf(PokemonPageDataListState(loading = true))
    val fetchAllPokemonsListState: State<PokemonPageDataListState> = _fetchAllPokemons

    private val _fetchAllTypes = mutableStateOf(PokemonTypeState(loading = true))
    val fetchAllPokemonsTypesState: MutableState<PokemonTypeState> = _fetchAllTypes

    private val _fetchByType = mutableStateOf(PokemonPerTypeState(loading = true))
    val fetchPokemonsByTypeState: MutableState<PokemonPerTypeState> = _fetchByType


    init {
        getPagingFlow()
        getAllTypes()
    }

    private fun getAllTypes() {

            fetchPokemonsTypes().onEach {
                when (it) {
                    is Resources.Success -> _fetchAllTypes.value =
                        PokemonTypeState(pokemonTypeData = it.data)

                    is Resources.Loading -> _fetchAllTypes.value = PokemonTypeState(true)
                    is Resources.Error -> _fetchAllTypes.value =
                        PokemonTypeState(error = it.message)

                }

        }.launchIn(viewModelScope)
    }

    fun getPokemonsByType(type: String) {


            fetchPokemonByType(type).onEach {
                when (it) {
                    is Resources.Success -> _fetchByType.value =
                        PokemonPerTypeState(pokemonPerTypeData = it.data)

                    is Resources.Loading -> _fetchByType.value = PokemonPerTypeState(true)
                    is Resources.Error -> _fetchByType.value =
                        PokemonPerTypeState(error = it.message)

                }
            }.launchIn(viewModelScope)


    }

    private fun getPagingFlow() {
        fetchPokemons().onEach {

            when (it) {
                is Resources.Success -> _fetchAllPokemons.value =
                    PokemonPageDataListState(pokemonListData = it.data)

                is Resources.Loading -> _fetchAllPokemons.value = PokemonPageDataListState(true)
                is Resources.Error -> _fetchAllPokemons.value =
                    PokemonPageDataListState(error = it.message)

            }
        }.launchIn(viewModelScope)
    }


}