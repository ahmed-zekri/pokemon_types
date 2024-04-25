package com.zekri_ahmed.pokemontypes.presentation.pokemon_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zekri_ahmed.pokemontypes.data.common.ID_PARAM
import com.zekri_ahmed.pokemontypes.data.dto.PokemonInfo
import com.zekri_ahmed.pokemontypes.domain.use_cases.FetchPokemonById
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonsDetailsViewModel @Inject constructor(
    private val fetchPokemonById: FetchPokemonById,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {
    private val id = savedStateHandle.get<String>(ID_PARAM)
    private val _fetchPokemonById = mutableStateOf<PokemonInfo?>(null)
    val fetchPokemonByIdState: State<PokemonInfo?> = _fetchPokemonById
init {
    getPokemonById()
}

    private fun getPokemonById() {
        viewModelScope.launch {
            id?.let {
                fetchPokemonById(id).collect {
                    _fetchPokemonById.value = it


                }
            }

        }
    }


}