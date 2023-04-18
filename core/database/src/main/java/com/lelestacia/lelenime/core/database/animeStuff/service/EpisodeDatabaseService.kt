package com.lelestacia.lelenime.core.database.animeStuff.service

import com.lelestacia.lelenime.core.database.animeStuff.dao.EpisodeDao
import com.lelestacia.lelenime.core.database.animeStuff.entity.episode.EpisodeEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EpisodeDatabaseService @Inject constructor(
    private val episodeDao: EpisodeDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IEpisodeDatabaseService {
    override suspend fun insertOrUpdateEpisodes(episodes: List<EpisodeEntity>) {
        withContext(ioDispatcher) {
            episodeDao.insertOrUpdateEpisode(episodes = episodes)
        }
    }

    override suspend fun getEpisodesByAnimeID(animeID: Int): List<EpisodeEntity> {
        return withContext(ioDispatcher) {
            episodeDao.getEpisodeByAnimeID(animeID = animeID)
        }
    }
}
