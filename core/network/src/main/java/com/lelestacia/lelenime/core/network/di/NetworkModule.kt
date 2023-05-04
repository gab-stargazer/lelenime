package com.lelestacia.lelenime.core.network.di

import com.lelestacia.lelenime.core.network.endpoint.AnimeAPI
import com.lelestacia.lelenime.core.network.endpoint.CharacterAPI
import com.lelestacia.lelenime.core.network.source.anime.AnimeNetworkService
import com.lelestacia.lelenime.core.network.source.anime.IAnimeNetworkService
import com.lelestacia.lelenime.core.network.source.character.CharacterNetworkService
import com.lelestacia.lelenime.core.network.source.character.ICharacterNetworkService
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

    @Provides
    @Singleton
    fun provideCharacterDataSource(characterAPI: CharacterAPI): ICharacterNetworkService =
        CharacterNetworkService(characterAPI)
}
