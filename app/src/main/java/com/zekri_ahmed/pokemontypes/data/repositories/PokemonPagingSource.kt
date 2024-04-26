package com.zekri_ahmed.pokemontypes.data.repositories

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.zekri_ahmed.pokemontypes.data.dto.Pokemon
import com.zekri_ahmed.pokemontypes.data.remote.PokemonApi
import javax.inject.Inject

class PokemonPagingSource @Inject constructor(private val api: PokemonApi) :
    PagingSource<Int, Pokemon>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {

        return try {
            val position = params.key ?: 0
            val response = api.getAllPokemons(position * 30)
            LoadResult.Page(
                data = response.body()?.results ?: listOf(),
                prevKey = if (position == 0) null else position - 1,
                nextKey = position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Pokemon>) =
        state.anchorPosition


}