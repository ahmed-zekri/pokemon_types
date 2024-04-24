package com.zekri_ahmed.pokemontypes.data.remote

import com.zekri_ahmed.pokemontypes.data.dto.PokemonResults
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface PokemonApi {

    @GET("pokemon")
    suspend fun getAllPokemons(

        @Query("offset") offset: Int,
        @Query("limit") limit: Int = 20,
    ): Response<PokemonResults>

}
