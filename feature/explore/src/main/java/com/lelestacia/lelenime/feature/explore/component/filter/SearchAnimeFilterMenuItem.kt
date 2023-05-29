package com.lelestacia.lelenime.feature.explore.component.filter

import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.lelestacia.lelenime.core.common.requestParam.AnimeRatingDropDownMenu
import com.lelestacia.lelenime.core.common.requestParam.AnimeStatusDropDownMenu
import com.lelestacia.lelenime.core.common.requestParam.AnimeTypeDropDownMenu
import com.lelestacia.lelenime.feature.explore.R
import com.lelestacia.lelenime.feature.explore.stateAndEvent.SearchAnimeFilter

@Composable
fun SearchAnimeFilterMenuItem(
    searchAnimeFilter: SearchAnimeFilter,
    onSearchAnimeFilterChanged: (SearchAnimeFilter) -> Unit,
    isAnimeTypeMenuOpened: Boolean,
    onAnimeTypeMenuStateChanged: (Boolean) -> Unit,
    isAnimeStatusMenuOpened: Boolean,
    onAnimeStatusMenuStateChanged: (Boolean) -> Unit,
    isAnimeRatingMenuOpened: Boolean,
    onAnimeRatingMenuStateChanged: (Boolean) -> Unit
) {
    DropdownMenuItem(
        text = {
            Text(text = stringResource(id = R.string.anime_type))
        },
        onClick = {
            onAnimeTypeMenuStateChanged(true)
        }
    )
    AnimeTypeDropDownMenu(
        selectedAnimeType = searchAnimeFilter.animeType,
        isAnimeTypeMenuOpened = isAnimeTypeMenuOpened,
        onDismiss = {
            onAnimeTypeMenuStateChanged(it)
        },
        onAnimeTypeSelected = { selectedType ->
            onSearchAnimeFilterChanged(
                searchAnimeFilter.copy(
                    animeType = selectedType
                )
            )
        }
    )
    Divider()
    DropdownMenuItem(
        text = {
            Text(text = stringResource(id = R.string.anime_status))
        },
        onClick = {
            onAnimeStatusMenuStateChanged(true)
        }
    )
    AnimeStatusDropDownMenu(
        selectedAnimeStatus = searchAnimeFilter.animeStatus,
        isAnimeStatusMenuOpened = isAnimeStatusMenuOpened,
        onDismiss = {
            onAnimeStatusMenuStateChanged(it)
        },
        onAnimeStatusSelected = { selectedAnimeStatus ->
            onSearchAnimeFilterChanged(
                searchAnimeFilter.copy(
                    animeStatus = selectedAnimeStatus
                )
            )
        }
    )
    Divider()
    DropdownMenuItem(
        text = {
            Text(text = stringResource(id = R.string.anime_rating))
        },
        onClick = {
            onAnimeRatingMenuStateChanged(true)
        }
    )
    AnimeRatingDropDownMenu(
        selectedAnimeRating = searchAnimeFilter.animeRating,
        isAnimeRatingMenuOpened = isAnimeRatingMenuOpened,
        onDismiss = {
            onAnimeRatingMenuStateChanged(it)
        },
        onAnimeRatingSelected = { selectedAnimeRating ->
            onSearchAnimeFilterChanged(
                searchAnimeFilter.copy(
                    animeRating = selectedAnimeRating
                )
            )
        }
    )
}
