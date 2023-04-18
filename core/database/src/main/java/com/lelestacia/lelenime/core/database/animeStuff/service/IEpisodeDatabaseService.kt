package com.lelestacia.lelenime.core.database.animeStuff.service

import com.lelestacia.lelenime.core.database.animeStuff.entity.episode.EpisodeEntity

interface IEpisodeDatabaseService {
    suspend fun insertOrUpdateEpisodes(episodes: List<EpisodeEntity>)
    suspend fun getEpisodesByAnimeID(animeID: Int): List<EpisodeEntity>
}
