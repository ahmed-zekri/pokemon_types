package com.zekri_ahmed.pokemontypes.data.remote

import com.zekri_ahmed.pokemontypes.data.dto.Pokemon
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface RetrofitService {

    @GET("/")
    suspend fun getAllPokemons(

        @Query("offset") offset: Int,
        @Query("limit") limit: Int = 10000,
    ): Response<List<Pokemon>>

}
