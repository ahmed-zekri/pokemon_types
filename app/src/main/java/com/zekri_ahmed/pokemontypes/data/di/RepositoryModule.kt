package com.zekri_ahmed.pokemontypes.data.di

import com.zekri_ahmed.pokemontypes.data.repositories.MainRepositoryImpl
import com.zekri_ahmed.pokemontypes.domain.repositories.MainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun providesPokemonRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository
}