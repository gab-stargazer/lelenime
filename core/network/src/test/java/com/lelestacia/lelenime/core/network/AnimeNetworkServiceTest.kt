package com.lelestacia.lelenime.core.network

import androidx.paging.PagingSource
import com.lelestacia.lelenime.core.network.endpoint.AnimeAPI
import com.lelestacia.lelenime.core.network.model.GenericPaginationResponse
import com.lelestacia.lelenime.core.network.model.PaginationResponse
import com.lelestacia.lelenime.core.network.source.anime.AnimeNetworkService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.BlockJUnit4ClassRunner
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(BlockJUnit4ClassRunner::class)
class AnimeNetworkServiceTest {

    @get:Rule
    val mockKRule = MockKRule(this)

    @MockK
    lateinit var animeAPI: AnimeAPI
    private lateinit var animeNetworkService: AnimeNetworkService
    private val ioException: Throwable = Throwable(IOException("Test Error"))

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        animeNetworkService = AnimeNetworkService(animeAPI = animeAPI)
    }

    @Test
    fun `Test for Airing Anime`() = runTest {
        val firstPage = GenericPaginationResponse(
            data = listOfAiringAnime,
            pagination = PaginationResponse(
                lastVisiblePage = 3,
                hasNextPage = false
            )
        )
        coEvery { animeAPI.getCurrentSeason(page = 1) } answers { firstPage }
        val actualPagingData = animeNetworkService.getAiringAnime().load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 3,
                placeholdersEnabled = false
            )
        )
        Assert.assertEquals(
            PagingSource.LoadResult.Page(
                data = firstPage.data,
                prevKey = null,
                nextKey = null
            ),
            actualPagingData
        )
        Assert.assertTrue(actualPagingData is PagingSource.LoadResult.Page)
    }

    @Test
    fun `Test for Airing Anime with Error`() = runTest {
        coEvery { animeAPI.getCurrentSeason(page = 1) } throws ioException
        Assert.assertThrows(ioException.javaClass) {
            runBlocking {
                animeNetworkService.getAiringAnime().load(
                    PagingSource.LoadParams.Refresh(
                        key = null,
                        loadSize = 3,
                        placeholdersEnabled = false
                    )
                )
            }
        }
    }

    @Test
    fun `Test for Upcoming Anime`() = runTest {
        val firstPage = GenericPaginationResponse(
            data = listOfAiringAnime,
            pagination = PaginationResponse(
                lastVisiblePage = 3,
                hasNextPage = false
            )
        )
        coEvery { animeAPI.getUpcomingSeason(page = 1) } answers { firstPage }
        val actualPagingData = animeNetworkService.getUpcomingAnime().load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 3,
                placeholdersEnabled = false
            )
        )
        Assert.assertEquals(
            PagingSource.LoadResult.Page(
                data = firstPage.data,
                prevKey = null,
                nextKey = null
            ),
            actualPagingData
        )
    }

    @Test
    fun `Test for Upcoming Anime with Error`() = runTest {
        coEvery { animeAPI.getUpcomingSeason(page = 1) } throws ioException
        Assert.assertThrows(ioException.javaClass) {
            runBlocking {
                animeNetworkService.getUpcomingAnime().load(
                    PagingSource.LoadParams.Refresh(
                        key = null,
                        loadSize = 3,
                        placeholdersEnabled = false
                    )
                )
            }
        }
    }

    @Test
    fun `Test for Popular Anime`() = runTest {
        val firstPage = GenericPaginationResponse(
            data = listOfAiringAnime,
            pagination = PaginationResponse(
                lastVisiblePage = 3,
                hasNextPage = false
            )
        )
        coEvery { animeAPI.getPopularAnime(page = 1) } answers { firstPage }
        val actualPagingData = animeNetworkService.getPopularAnime().load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 3,
                placeholdersEnabled = false
            )
        )
        Assert.assertEquals(
            PagingSource.LoadResult.Page(
                data = firstPage.data,
                prevKey = null,
                nextKey = null
            ),
            actualPagingData
        )
    }

    @Test
    fun `Test for Popular Anime with Error`() = runTest {
        coEvery { animeAPI.getPopularAnime(page = 1) } throws ioException
        Assert.assertThrows(ioException.javaClass) {
            runBlocking {
                animeNetworkService.getPopularAnime().load(
                    PagingSource.LoadParams.Refresh(
                        key = null,
                        loadSize = 3,
                        placeholdersEnabled = false
                    )
                )
            }
        }
    }

    @Test
    fun `Test for Search Anime`() = runTest {
        val firstPage = GenericPaginationResponse(
            data = listOfAiringAnime,
            pagination = PaginationResponse(
                lastVisiblePage = 3,
                hasNextPage = false
            )
        )
        coEvery { animeAPI.getAnimeSearch(q = "", page = 1) } answers { firstPage }
        val actualPagingData = animeNetworkService.getAnimeSearch(searchQuery = "").load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 3,
                placeholdersEnabled = false
            )
        )
        Assert.assertEquals(
            PagingSource.LoadResult.Page(
                data = firstPage.data,
                prevKey = null,
                nextKey = null
            ),
            actualPagingData
        )
    }

    @Test
    fun `Test for Search Anime with Error`() = runTest {
        coEvery { animeAPI.getAnimeSearch(q = "", page = 1) } throws ioException
        Assert.assertThrows(ioException.javaClass) {
            runBlocking {
                animeNetworkService.getAnimeSearch(searchQuery = "").load(
                    PagingSource.LoadParams.Refresh(
                        key = null,
                        loadSize = 3,
                        placeholdersEnabled = false
                    )
                )
            }
        }
    }
}
