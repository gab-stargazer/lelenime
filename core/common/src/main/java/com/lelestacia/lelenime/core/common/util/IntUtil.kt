package com.lelestacia.lelenime.core.common.util

import com.lelestacia.lelenime.core.common.request.param.AnimeGenre

fun Int?.isNotNull(): Boolean {
    return this != null
}

fun List<AnimeGenre>.toQueryParam(): String? {
    return when {
        this.isEmpty() -> {
            null
        }

        else -> {
            var queryParam = ""
            this.forEachIndexed { index, i ->
                queryParam += if (index == 0) {
                    i.id.toString()
                } else {
                    ", ${i.id}"
                }
            }
            queryParam
        }
    }
}
