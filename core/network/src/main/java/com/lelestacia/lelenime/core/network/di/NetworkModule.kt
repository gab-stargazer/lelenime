package com.lelestacia.lelenime.core.network.di

import com.lelestacia.lelenime.core.network.endpoint.AnimeAPI
import com.lelestacia.lelenime.core.network.source.AnimeNetworkService
import com.lelestacia.lelenime.core.network.source.IAnimeNetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideAnimeDataSource(animeAPI: AnimeAPI): IAnimeNetworkService =
        AnimeNetworkService(animeAPI)
}
