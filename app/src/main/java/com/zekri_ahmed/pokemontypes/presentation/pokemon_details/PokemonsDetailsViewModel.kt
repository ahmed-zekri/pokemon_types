package com.zekri_ahmed.pokemontypes.presentation.pokemon_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zekri_ahmed.pokemontypes.data.dto.PokemonInfo
import com.zekri_ahmed.pokemontypes.domain.use_cases.FetchPokemonById
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonsDetailsViewModel @Inject constructor(private val fetchPokemonById: FetchPokemonById) :
    ViewModel() {
    private val id = "1"
    private val _fetchPokemonById = mutableStateOf<PokemonInfo?>(null)
    val fetchPokemonByIdState: State<PokemonInfo?> = _fetchPokemonById


    init {
        getPokemonById()
    }

    private fun getPokemonById() {
        viewModelScope.launch {
            fetchPokemonById(id).collect{
                _fetchPokemonById.value =it


            }
        }
         }


}