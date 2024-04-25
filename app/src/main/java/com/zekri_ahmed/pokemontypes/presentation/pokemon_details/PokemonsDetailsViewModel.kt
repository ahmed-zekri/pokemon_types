package com.zekri_ahmed.pokemontypes.presentation.pokemon_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zekri_ahmed.pokemontypes.common.ID_PARAM
import com.zekri_ahmed.pokemontypes.common.Resources
import com.zekri_ahmed.pokemontypes.domain.use_cases.FetchPokemonById
import com.zekri_ahmed.pokemontypes.presentation.pokemon_details.components.PokemonPerIdState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PokemonsDetailsViewModel @Inject constructor(
    private val fetchPokemonById: FetchPokemonById,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {
    private val id = savedStateHandle.get<String>(ID_PARAM)
    private val _fetchPokemonById = mutableStateOf(PokemonPerIdState(loading = true))
    val fetchPokemonByIdState: State<PokemonPerIdState> = _fetchPokemonById

    init {
        getPokemonById()
    }

    private fun getPokemonById() {

            id?.let {
                fetchPokemonById(id).onEach {
                    when (it) {
                        is Resources.Success -> _fetchPokemonById.value =
                            PokemonPerIdState(pokemonPerIdData = it.data)

                        is Resources.Loading -> _fetchPokemonById.value = PokemonPerIdState(true)
                        is Resources.Error -> _fetchPokemonById.value =
                            PokemonPerIdState(error = it.message)

                    }



            }.launchIn(viewModelScope)

        }
    }


}