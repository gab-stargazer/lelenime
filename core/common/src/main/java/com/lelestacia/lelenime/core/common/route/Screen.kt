package com.lelestacia.lelenime.core.common.route

sealed class Screen(val route: String) {
    object Explore : Screen("explore")
    object Collection : Screen("collection")
    object More : Screen("more")
    object DetailAnimeScreen : Screen("anime/{mal_id}") {
        fun createRoute(animeID: Int): String {
            return this.route.replace(
                oldValue = "{mal_id}",
                newValue = animeID.toString()
            )
        }
    }
    object FullSynopsisScreen : Screen("anime/synopsis/{mal_id}") {
        fun createRoute(malID: Int): String {
            return this.route.replace(
                oldValue = "{mal_id}",
                newValue = malID.toString()
            )
        }
    }
    object DetailCharacterScreen : Screen("anime/character/{mal_id}") {
        fun createRoute(characterID: Int): String {
            return this.route.replace(
                oldValue = "{mal_id}",
                newValue = characterID.toString()
            )
        }
    }

    object About : Screen("about")
    object Settings : Screen("settings")
}
