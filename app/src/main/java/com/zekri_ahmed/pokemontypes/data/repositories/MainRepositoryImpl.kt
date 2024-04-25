package com.zekri_ahmed.pokemontypes.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.zekri_ahmed.pokemontypes.data.dto.Pokemon
import com.zekri_ahmed.pokemontypes.data.dto.PokemonByTypeResults
import com.zekri_ahmed.pokemontypes.data.dto.PokemonTypeResults
import com.zekri_ahmed.pokemontypes.data.remote.PokemonApi
import com.zekri_ahmed.pokemontypes.domain.repositories.MainRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val pokemonPagingSource: PokemonPagingSource,
    private val pokemonApi: PokemonApi
) :
    MainRepository {

    override fun getAllPokemons(): Flow<PagingData<Pokemon>> {

        return Pager(
            config = PagingConfig(
                pageSize = 30,
                enablePlaceholders = false,
                initialLoadSize = 2
            ),
            pagingSourceFactory = {
                pokemonPagingSource
            }, initialKey = 1
        ).flow
    }

    override suspend fun getPokemonById(id: String) =
        pokemonApi.getPokemonById(id)

    override suspend fun getPokemonByType(type: String): Response<PokemonByTypeResults>
    = pokemonApi.getPokemonByType(type)


    override suspend fun getPokemonTypes(): Response<PokemonTypeResults> =
        pokemonApi.getPokemonTypes()
}
