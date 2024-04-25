package com.zekri_ahmed.pokemontypes.data.di

import com.zekri_ahmed.pokemontypes.common.BASE_URL
import com.zekri_ahmed.pokemontypes.data.remote.PokemonApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Provides
    @Singleton
    fun providesApi(): PokemonApi {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.HEADERS
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor).build()
        return Retrofit.Builder().client(client)
            .addConverterFactory(GsonConverterFactory.create())

            .baseUrl(BASE_URL)
            .build().create(PokemonApi::class.java)

    }
}