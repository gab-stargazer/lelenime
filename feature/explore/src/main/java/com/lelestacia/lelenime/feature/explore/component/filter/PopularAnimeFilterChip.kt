package com.lelestacia.lelenime.feature.explore.component.filter

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lelestacia.lelenime.core.common.component.LelenimeFilterChip
import com.lelestacia.lelenime.core.common.requestParam.AnimeStatusDropDownMenu
import com.lelestacia.lelenime.core.common.requestParam.AnimeTypeDropDownMenu
import com.lelestacia.lelenime.core.common.theme.LelenimeTheme
import com.lelestacia.lelenime.core.common.theme.spacing
import com.lelestacia.lelenime.feature.explore.R
import com.lelestacia.lelenime.feature.explore.stateAndEvent.PopularAnimeFilter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopularAnimeFilterChip(
    popularAnimeFilter: PopularAnimeFilter,
    onPopularAnimeFilterChanged: (PopularAnimeFilter) -> Unit
) {
    var isMenuOpened by remember {
        mutableStateOf(false)
    }
    var isAnimeTypeMenuOpened by remember {
        mutableStateOf(false)
    }
    var isAnimeStatusMenuOpened by remember {
        mutableStateOf(false)
    }
    val isActive = popularAnimeFilter.animeStatus != null || popularAnimeFilter.animeType != null
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
            onClick = {
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
                selectedAnimeType = popularAnimeFilter.animeType,
                isAnimeTypeMenuOpened = isAnimeTypeMenuOpened,
                onDismiss = {
                    isAnimeTypeMenuOpened = it
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
                    Text(text = stringResource(id = R.string.anime_status))
                },
                onClick = {
                    isAnimeStatusMenuOpened = true
                }
            )
            AnimeStatusDropDownMenu(
                selectedAnimeStatus = popularAnimeFilter.animeStatus,
                isAnimeStatusMenuOpened = isAnimeStatusMenuOpened,
                onDismiss = {
                    isAnimeStatusMenuOpened = it
                },
                onAnimeStatusSelected = { selectedAnimeStatus ->
                    onPopularAnimeFilterChanged(
                        popularAnimeFilter.copy(
                            animeStatus = selectedAnimeStatus
                        )
                    )
                }
            )
            OutlinedButton(
                onClick = { },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.small)
            ) {
                Text(text = "Apply")
            }
        }
    }
}

@Preview
@Composable
fun PopularAnimeFilterButtonPreview() {
    LelenimeTheme(dynamicColor = false) {
        var filter by remember {
            mutableStateOf(PopularAnimeFilter())
        }
        PopularAnimeFilterChip(
            popularAnimeFilter = filter,
            onPopularAnimeFilterChanged = {
                filter = it
            }
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
fun PopularAnimeFilterButtonPreviewNightMode() {
    LelenimeTheme(dynamicColor = false) {
        PopularAnimeFilterChip(
            popularAnimeFilter = PopularAnimeFilter(),
            onPopularAnimeFilterChanged = {}
        )
    }
}
