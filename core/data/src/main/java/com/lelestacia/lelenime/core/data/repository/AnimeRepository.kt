package com.lelestacia.lelenime.core.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.lelestacia.lelenime.core.common.Resource
import com.lelestacia.lelenime.core.data.JikanErrorParserUtil
import com.lelestacia.lelenime.core.data.mapper.asAnime
import com.lelestacia.lelenime.core.data.mapper.asNewEntity
import com.lelestacia.lelenime.core.database.animeStuff.entity.anime.AnimeEntity
import com.lelestacia.lelenime.core.database.animeStuff.service.IAnimeDatabaseService
import com.lelestacia.lelenime.core.model.Anime
import com.lelestacia.lelenime.core.network.model.anime.AnimeResponse
import com.lelestacia.lelenime.core.network.source.anime.IAnimeNetworkService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.util.Date
import javax.inject.Inject

class AnimeRepository @Inject constructor(
    private val animeNetworkService: IAnimeNetworkService,
    private val animeDatabaseService: IAnimeDatabaseService,
    private val errorParserUtil: JikanErrorParserUtil,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IAnimeRepository {

    override suspend fun insertOrUpdateAnimeHistory(anime: Anime) {
        withContext(ioDispatcher) {
            val localAnime: AnimeEntity? = animeDatabaseService.getAnimeByAnimeID(anime.malID)
            val isAnimeExist = localAnime != null

            if (isAnimeExist) {
                val updatedHistory: AnimeEntity = (localAnime as AnimeEntity).copy(
                    image = anime.coverImages,
                    trailer = AnimeEntity.Trailer(
                        id = anime.trailer?.youtubeId,
                        url = anime.trailer?.url,
                        image = anime.trailer?.images
                    ),
                    episodes = anime.episodes,
                    status = anime.status,
                    airing = anime.airing,
                    startedDate = anime.startedDate,
                    finishedDate = anime.finishedDate,
                    score = anime.score,
                    scoredBy = anime.scoredBy,
                    rank = anime.rank,
                    lastViewed = Date(),
                    updatedAt = Date()
                )
                animeDatabaseService.updateAnime(updatedHistory)
                return@withContext
            }

            val newAnime = anime.asNewEntity()
            animeDatabaseService.insertOrReplaceAnime(newAnime)
        }
    }

    override suspend fun updateAnimeFavoriteByAnimeID(animeID: Int) {
        withContext(ioDispatcher) {
            val anime = animeDatabaseService.getAnimeByAnimeID(animeID = animeID)
            anime?.let { oldAnime ->
                val newAnime = oldAnime.copy(isFavorite = !oldAnime.isFavorite)
                animeDatabaseService.updateAnime(newAnime)
            }
        }
    }

    override fun getAiringAnime(): Flow<PagingData<Anime>> {
        return Pager(
            config = PagingConfig(
                pageSize = 25,
                prefetchDistance = 15,
                enablePlaceholders = false,
                initialLoadSize = 25
            ),
            pagingSourceFactory = {
                animeNetworkService.getAiringAnime()
            }
        ).flow.map { pagingData ->
            pagingData.map(AnimeResponse::asAnime)
        }
    }

    override fun getUpcomingAnime(type: String?): Flow<PagingData<Anime>> {
        return Pager(
            config = PagingConfig(
                pageSize = 25,
                prefetchDistance = 15,
                enablePlaceholders = false,
                initialLoadSize = 25
            ),
            pagingSourceFactory = {
                animeNetworkService.getUpcomingAnime(
                    type = type
                )
            }
        ).flow.map { pagingData ->
            pagingData.map(AnimeResponse::asAnime)
        }
    }

    override fun getPopularAnime(
        type: String?,
        status: String?
    ): Flow<PagingData<Anime>> {
        return Pager(
            config = PagingConfig(
                pageSize = 25,
                prefetchDistance = 15,
                enablePlaceholders = false,
                initialLoadSize = 25
            ),
            pagingSourceFactory = {
                animeNetworkService.getPopularAnime(
                    type = type,
                    status = status
                )
            }
        ).flow.map { pagingData ->
            pagingData.map(AnimeResponse::asAnime)
        }
    }

    override fun getAnimeSearch(
        searchQuery: String,
        type: String?,
        status: String?,
        rating: String?
    ): Flow<PagingData<Anime>> {
        return Pager(
            config = PagingConfig(
                pageSize = 25,
                prefetchDistance = 15,
                enablePlaceholders = false,
                initialLoadSize = 25
            ),
            pagingSourceFactory = {
                animeNetworkService.getAnimeSearch(
                    searchQuery = searchQuery,
                    type = type,
                    status = status,
                    rating = rating
                )
            }
        ).flow.map { pagingData ->
            pagingData.map(AnimeResponse::asAnime)
        }
    }

    override fun getAnimeHistory(): Flow<PagingData<Anime>> {
        return Pager(
            config = PagingConfig(
                pageSize = 25,
                prefetchDistance = 15,
                enablePlaceholders = false,
                initialLoadSize = 25
            ),
            pagingSourceFactory = {
                animeDatabaseService.getAnimeHistory()
            }
        ).flow.map { pagingData ->
            pagingData.map(AnimeEntity::asAnime)
        }
    }

    override fun getAnimeFavorite(): Flow<PagingData<Anime>> {
        return Pager(
            config = PagingConfig(
                pageSize = 25,
                prefetchDistance = 15,
                enablePlaceholders = false,
                initialLoadSize = 25
            ),
            pagingSourceFactory = {
                animeDatabaseService.getAnimeFavorite()
            }
        ).flow.map { pagingData ->
            pagingData.map(AnimeEntity::asAnime)
        }
    }

    override fun getAnimeFromLocalDatabaseByAnimeID(animeID: Int): Flow<Resource<Anime>> {
        return animeDatabaseService
            .getAndSubscribeAnimeByAnimeID(animeID = animeID)
            .map { animeEntity ->
                Resource.Success(data = animeEntity.asAnime())
            }
    }

    override fun parseThrowable(t: Throwable): String {
        return errorParserUtil(t)
    }
}
