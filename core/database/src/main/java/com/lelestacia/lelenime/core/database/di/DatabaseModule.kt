package com.lelestacia.lelenime.core.database.di

import android.content.Context
import androidx.room.Room
import com.lelestacia.lelenime.core.database.animeStuff.dao.AnimeCharacterCrossRefDao
import com.lelestacia.lelenime.core.database.animeStuff.dao.AnimeDao
import com.lelestacia.lelenime.core.database.animeStuff.dao.CharacterDao
import com.lelestacia.lelenime.core.database.animeStuff.dao.CharacterVoiceActorCrossRefDao
import com.lelestacia.lelenime.core.database.animeStuff.dao.EpisodeDao
import com.lelestacia.lelenime.core.database.animeStuff.dao.VoiceActorDao
import com.lelestacia.lelenime.core.database.animeStuff.database.AnimeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(
        @ApplicationContext context: Context
    ): AnimeDatabase =
        Room.databaseBuilder(
            context,
            AnimeDatabase::class.java,
            "anime.db"
        ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideAnimeDao(animeDatabase: AnimeDatabase): AnimeDao = animeDatabase.animeDao()

    @Provides
    @Singleton
    fun provideAnimeCharacterCrossRefDao(animeDatabase: AnimeDatabase): AnimeCharacterCrossRefDao =
        animeDatabase.animeCharacterCrossRefDao()

    @Provides
    @Singleton
    fun provideCharacterDao(animeDatabase: AnimeDatabase): CharacterDao =
        animeDatabase.characterDao()

    @Provides
    @Singleton
    fun provideCharacterVoiceActorCrossRefDao(animeDatabase: AnimeDatabase): CharacterVoiceActorCrossRefDao =
        animeDatabase.characterVoiceActorCrossRefDao()

    @Provides
    @Singleton
    fun provideEpisodeDao(animeDatabase: AnimeDatabase): EpisodeDao = animeDatabase.episodeDao()

    @Provides
    @Singleton
    fun provideVoiceActorDao(animeDatabase: AnimeDatabase): VoiceActorDao =
        animeDatabase.voiceActorDao()
}
