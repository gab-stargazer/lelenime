package com.lelestacia.lelenime.core.data.di

import com.lelestacia.lelenime.core.data.JikanErrorParserUtil
import com.lelestacia.lelenime.core.data.repository.AnimeRepository
import com.lelestacia.lelenime.core.data.repository.CharacterRepository
import com.lelestacia.lelenime.core.data.repository.IAnimeRepository
import com.lelestacia.lelenime.core.data.repository.ICharacterRepository
import com.lelestacia.lelenime.core.data.repository.IUserPreferencesRepository
import com.lelestacia.lelenime.core.data.repository.UserPreferencesRepository
import com.lelestacia.lelenime.core.database.animeStuff.service.IAnimeDatabaseService
import com.lelestacia.lelenime.core.database.animeStuff.service.ICharacterDatabaseService
import com.lelestacia.lelenime.core.database.userPreferences.IUserPreferencesService
import com.lelestacia.lelenime.core.network.source.anime.IAnimeNetworkService
import com.lelestacia.lelenime.core.network.source.character.ICharacterNetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideErrorParser(): JikanErrorParserUtil = JikanErrorParserUtil()

    @Provides
    @Singleton
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

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

    @Singleton
    @Provides
    fun provideCharacterRepository(
        characterNetworkService: ICharacterNetworkService,
        animeNetworkService: IAnimeNetworkService,
        characterDatabaseService: ICharacterDatabaseService,
        errorParserUtil: JikanErrorParserUtil,
        ioDispatcher: CoroutineDispatcher
    ):ICharacterRepository =
        CharacterRepository(
            characterNetworkService = characterNetworkService,
            animeNetworkService = animeNetworkService,
            characterDatabaseService = characterDatabaseService,
            errorParser = errorParserUtil,
            ioDispatcher = ioDispatcher
        )
}
