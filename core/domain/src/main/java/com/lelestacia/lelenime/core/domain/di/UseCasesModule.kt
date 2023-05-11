package com.lelestacia.lelenime.core.domain.di

import com.lelestacia.lelenime.core.data.repository.CharacterRepository
import com.lelestacia.lelenime.core.data.repository.IAnimeRepository
import com.lelestacia.lelenime.core.data.repository.ICharacterRepository
import com.lelestacia.lelenime.core.data.repository.IUserPreferencesRepository
import com.lelestacia.lelenime.core.domain.usecases.collection.CollectionUseCases
import com.lelestacia.lelenime.core.domain.usecases.collection.ICollectionUseCases
import com.lelestacia.lelenime.core.domain.usecases.detailAnime.DetailAnimeUseCases
import com.lelestacia.lelenime.core.domain.usecases.detailAnime.IDetailAnimeUseCases
import com.lelestacia.lelenime.core.domain.usecases.detailCharacter.DetailCharacterUseCases
import com.lelestacia.lelenime.core.domain.usecases.detailCharacter.IDetailCharacterUseCases
import com.lelestacia.lelenime.core.domain.usecases.explore.ExploreUseCases
import com.lelestacia.lelenime.core.domain.usecases.explore.IExploreUseCases
import com.lelestacia.lelenime.core.domain.usecases.settings.IUserPreferencesUseCases
import com.lelestacia.lelenime.core.domain.usecases.settings.UserPreferencesUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCasesModule {

    @Provides
    @ViewModelScoped
    fun provideExploreAnimeUseCases(animeRepository: IAnimeRepository): IExploreUseCases =
        ExploreUseCases(
            repository = animeRepository
        )

    @Provides
    @ViewModelScoped
    fun provideCollectionAnimeUseCases(animeRepository: IAnimeRepository): ICollectionUseCases =
        CollectionUseCases(
            repository = animeRepository
        )

    @Provides
    @ViewModelScoped
    fun provideDetailAnimeUseCases(
        animeRepository: IAnimeRepository,
        characterRepository: ICharacterRepository,
        preferencesRepository: IUserPreferencesRepository
    ): IDetailAnimeUseCases =
        DetailAnimeUseCases(
            animeRepository = animeRepository,
            characterRepository = characterRepository,
            preferencesRepository = preferencesRepository
        )

    @Provides
    @ViewModelScoped
    fun provideUserPreferencesUseCases(
        userPreferencesRepository: IUserPreferencesRepository
    ): IUserPreferencesUseCases =
        UserPreferencesUseCases(
            userPreferencesRepository = userPreferencesRepository
        )

    @Provides
    @ViewModelScoped
    fun provideDetailCharacterUseCases(
        characterRepository: CharacterRepository
    ): IDetailCharacterUseCases =
        DetailCharacterUseCases(characterRepository)
}
