package com.lelestacia.lelenime.feature.explore.component.filter

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.lelestacia.lelenime.core.common.requestParam.AnimeTypeDropDownMenu
import com.lelestacia.lelenime.feature.explore.R
import com.lelestacia.lelenime.feature.explore.stateAndEvent.UpcomingAnimeFilter

@Composable
fun UpcomingAnimeFilterMenuItem(
    upcomingAnimeFilter: UpcomingAnimeFilter,
    onUpcomingAnimeFilterChanged: (UpcomingAnimeFilter) -> Unit,
    isAnimeTypeMenuOpened: Boolean,
    onAnimeTypeMenuStateChanged: (Boolean) -> Unit
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
        selectedAnimeType = upcomingAnimeFilter.animeType,
        isAnimeTypeMenuOpened = isAnimeTypeMenuOpened,
        onDismiss = {
            onAnimeTypeMenuStateChanged(it)
        },
        onAnimeTypeSelected = { selectedType ->
            onUpcomingAnimeFilterChanged(
                upcomingAnimeFilter.copy(
                    animeType = selectedType
                )
            )
        }
    )
}
