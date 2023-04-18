package com.lelestacia.lelenime.core.data.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.lelestacia.lelenime.core.model.Anime
import com.lelestacia.lelenime.core.network.model.anime.AnimeResponse

class PagingDataMapper {

    fun mapResponseToAnime(pagingData: PagingData<AnimeResponse>): PagingData<Anime> {
        return pagingData.map { it.asAnime() }
    }
}
