package com.lelestacia.lelenime.core.common.request.param

enum class AnimeGenre(
    val id: Int,
    val title: String
) {
    ACTION(
        id = 1,
        title = "Action"
    ),
    ADVENTURE(
        id = 2,
        title = "Adventure"
    ),
    AVANT_GARDE(
        id = 5,
        title = "Avant Garde"
    ),
    AWARD_WINNING(
        id = 46,
        title = "Award Winning"
    ),
    BOYS_LOVE(
        id = 28,
        title = "Boys Love"
    ),
    COMEDY(
        id = 4,
        title = "Comedy"
    ),
    DRAMA(
        id = 8,
        title = "Drama"
    ),
    FANTASY(
        id = 10,
        title = "Fantasy"
    ),
    GIRLS_LOVE(
        id = 26,
        title = "Girls Love"
    ),
    GOURMET(
        id = 47,
        title = "Gourmet"
    ),
    HORROR(
        id = 14,
        title = "Horror"
    ),
    MYSTERY(
        id = 7,
        title = "Mystery"
    ),
    ROMANCE(
        id = 22,
        title = "Romance"
    ),
    SCI_FI(
        id = 24,
        title = "Sci-Fi"
    ),
    SLICE_OF_LIFE(
        id = 36,
        title = "Slice of Life"
    ),
    SPORTS(
        id = 30,
        title = "Sports"
    ),
    SUPERNATURAL(
        id = 37,
        title = "Supernatural"
    ),
    SUSPENSE(
        id = 41,
        title = "Suspense"
    )
}

val ListOfAnimeGenres: List<AnimeGenre?> = listOf(
    null,
    AnimeGenre.ACTION,
    AnimeGenre.ADVENTURE,
    AnimeGenre.AVANT_GARDE,
    AnimeGenre.AWARD_WINNING,
    AnimeGenre.BOYS_LOVE,
    AnimeGenre.COMEDY,
    AnimeGenre.DRAMA,
    AnimeGenre.FANTASY,
    AnimeGenre.GIRLS_LOVE,
    AnimeGenre.GOURMET,
    AnimeGenre.HORROR,
    AnimeGenre.MYSTERY,
    AnimeGenre.ROMANCE,
    AnimeGenre.SCI_FI,
    AnimeGenre.SLICE_OF_LIFE,
    AnimeGenre.SPORTS,
    AnimeGenre.SUPERNATURAL,
    AnimeGenre.SUSPENSE
)

fun getGenreByName(genreName: String): AnimeGenre? {
    ListOfAnimeGenres.onEach { animeGenre ->
        if (genreName == animeGenre?.title) return animeGenre
    }
    return null
}
