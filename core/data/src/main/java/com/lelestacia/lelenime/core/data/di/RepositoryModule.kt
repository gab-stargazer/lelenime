package com.lelestacia.lelenime.core.data.di

import com.lelestacia.lelenime.core.data.repository.AnimeRepository
import com.lelestacia.lelenime.core.data.repository.IAnimeRepository
import com.lelestacia.lelenime.core.data.repository.IUserPreferencesRepository
import com.lelestacia.lelenime.core.data.repository.UserPreferencesRepository
import com.lelestacia.lelenime.core.database.animeStuff.service.IAnimeDatabaseService
import com.lelestacia.lelenime.core.database.userPreferences.IUserPreferencesService
import com.lelestacia.lelenime.core.network.source.IAnimeNetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAnimeRepository(
        animeNetworkService: IAnimeNetworkService,
        animeDatabaseService: IAnimeDatabaseService
    ): IAnimeRepository =
        AnimeRepository(
            animeNetworkService = animeNetworkService,
            animeDatabaseService = animeDatabaseService
        )

    @Singleton
    @Provides
    fun provideUserPreferencesRepository(
        userPreferencesService: IUserPreferencesService
    ): IUserPreferencesRepository =
        UserPreferencesRepository(
            userPreferencesService = userPreferencesService
        )
}
