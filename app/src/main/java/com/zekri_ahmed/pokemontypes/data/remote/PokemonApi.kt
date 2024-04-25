package com.zekri_ahmed.pokemontypes.data.remote

import com.zekri_ahmed.pokemontypes.data.dto.PokemonByTypeResults
import com.zekri_ahmed.pokemontypes.data.dto.PokemonInfo
import com.zekri_ahmed.pokemontypes.data.dto.PokemonResults
import com.zekri_ahmed.pokemontypes.data.dto.PokemonTypeResults
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface PokemonApi {

    @GET("pokemon")
    suspend fun getAllPokemons(

        @Query("offset") offset: Int,
        @Query("limit") limit: Int = offset + 30,
    ): Response<PokemonResults>

    @GET("pokemon/{id}")
    suspend fun getPokemonById(
        @Path(value = "id")
        id: String
    ): Response<PokemonInfo>
    @GET("type/{type}")
    suspend fun getPokemonByType(
        @Path(value = "type")
        type: String
    ): Response<PokemonByTypeResults>

    @GET("type")
    suspend fun getPokemonTypes(
    ): Response<PokemonTypeResults>

}
