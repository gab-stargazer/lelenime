package com.lelestacia.lelenime.feature.explore.component.filter

import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.lelestacia.lelenime.core.common.R
import com.lelestacia.lelenime.core.common.requestParam.AnimeStatusDropDownMenu
import com.lelestacia.lelenime.core.common.requestParam.AnimeTypeDropDownMenu
import com.lelestacia.lelenime.feature.explore.stateAndEvent.PopularAnimeFilter

@Composable
fun PopularAnimeFilterMenuItem(
    popularAnimeFilter: PopularAnimeFilter,
    onPopularAnimeFilterChanged: (PopularAnimeFilter) -> Unit,
    isAnimeTypeMenuOpened: Boolean,
    onAnimeTypeMenuStateChanged: (Boolean) -> Unit,
    isAnimeStatusMenuOpened: Boolean,
    onAnimeStatusMenuStateChanged: (Boolean) -> Unit
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
        selectedAnimeType = popularAnimeFilter.animeType,
        isAnimeTypeMenuOpened = isAnimeTypeMenuOpened,
        onDismiss = {
            onAnimeTypeMenuStateChanged(it)
        },
        onAnimeTypeSelected = { selectedType ->
            onPopularAnimeFilterChanged(
                popularAnimeFilter.copy(
                    animeType = selectedType
                )
            )
        }
    )
    Divider()
    DropdownMenuItem(
        text = {
            Text(text = stringResource(id = com.lelestacia.lelenime.feature.explore.R.string.anime_status))
        },
        onClick = {
            onAnimeStatusMenuStateChanged(true)
        }
    )
    AnimeStatusDropDownMenu(
        selectedAnimeStatus = popularAnimeFilter.animeStatus,
        isAnimeStatusMenuOpened = isAnimeStatusMenuOpened,
        onDismiss = {
            onAnimeStatusMenuStateChanged(it)
        },
        onAnimeStatusSelected = { selectedAnimeStatus ->
            onPopularAnimeFilterChanged(
                popularAnimeFilter.copy(
                    animeStatus = selectedAnimeStatus
                )
            )
        }
    )
}
