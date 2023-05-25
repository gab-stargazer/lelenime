package com.lelestacia.lelenime.feature.explore.component.filter

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.lelestacia.lelenime.core.common.component.LelenimeFilterChip
import com.lelestacia.lelenime.core.common.requestParam.AnimeTypeDropDownMenu
import com.lelestacia.lelenime.feature.explore.R
import com.lelestacia.lelenime.feature.explore.stateAndEvent.UpcomingAnimeFilter

@Composable
fun UpcomingAnimeFilterChip(
    upcomingAnimeFilter: UpcomingAnimeFilter,
    onUpcomingAnimeFilterChanged: (UpcomingAnimeFilter) -> Unit
) {
    var isMenuOpened by remember {
        mutableStateOf(false)
    }
    var isAnimeTypeMenuOpened by remember {
        mutableStateOf(false)
    }
    val isActive = upcomingAnimeFilter.animeType != null
    Surface {
        LelenimeFilterChip(
            isActive = isActive,
            label = {
                Text(text = stringResource(id = R.string.filter))
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.FilterList,
                    contentDescription = stringResource(
                        id = R.string.filter_description
                    )
                )
            },
            trailingIcon = {},
            onClicked = {
                isMenuOpened = true
            }
        )
        DropdownMenu(
            expanded = isMenuOpened,
            onDismissRequest = { isMenuOpened = false }
        ) {
            DropdownMenuItem(
                text = {
                    Text(text = stringResource(id = R.string.anime_type))
                },
                onClick = {
                    isAnimeTypeMenuOpened = true
                }
            )
            AnimeTypeDropDownMenu(
                selectedAnimeType = upcomingAnimeFilter.animeType,
                isAnimeTypeMenuOpened = isAnimeTypeMenuOpened,
                onDismiss = {
                    isAnimeTypeMenuOpened = it
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
    }
}
