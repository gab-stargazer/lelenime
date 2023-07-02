package com.lelestacia.lelenime.core.common.request.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.lelestacia.lelenime.core.common.R
import com.lelestacia.lelenime.core.common.component.LelenimeFilterChip
import com.lelestacia.lelenime.core.common.request.param.AnimeStatus
import com.lelestacia.lelenime.core.common.request.param.ListOfAnimeStatus
import com.lelestacia.lelenime.core.common.theme.LelenimeTheme
import com.lelestacia.lelenime.core.common.theme.spacing
import java.util.Locale

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AnimeStatusFilterMenu(
    selectedAnimeStatus: AnimeStatus?,
    onAnimeStatusChanged: (AnimeStatus?) -> Unit
) {
    val listOfAnimeStatus: List<AnimeStatus?> = ListOfAnimeStatus
    val allAnimeStatus = stringResource(R.string.all_anime_status)
    Column(
        verticalArrangement = Arrangement.spacedBy(
            MaterialTheme.spacing.extraSmall
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.large)
    ) {
        Text(
            text = stringResource(id = R.string.anime_status),
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(
                MaterialTheme.spacing.small
            ),
            verticalAlignment = Alignment.CenterVertically,
            maxItemsInEachRow = 2
        ) {
            listOfAnimeStatus.forEach { status: AnimeStatus? ->
                val textToDisplay = status?.name
                    ?.lowercase()
                    ?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
                    ?: allAnimeStatus
                LelenimeFilterChip(
                    isActive = status == selectedAnimeStatus,
                    label = {
                        Text(text = textToDisplay)
                    },
                    leadingIcon = {},
                    trailingIcon = {},
                    onClicked = {
                        onAnimeStatusChanged(status)
                    },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewAnimeStatusFilterMenu() {
    LelenimeTheme(dynamicColor = false) {
        var animeStatus: AnimeStatus? by remember {
            mutableStateOf(null)
        }
        Surface {
            AnimeStatusFilterMenu(
                selectedAnimeStatus = animeStatus,
                onAnimeStatusChanged = { status: AnimeStatus? ->
                    animeStatus = status
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewAnimeStatusFilterMenuDarkMode() {
    LelenimeTheme(
        darkTheme = true,
        dynamicColor = false
    ) {
        var animeStatus: AnimeStatus? by remember {
            mutableStateOf(null)
        }
        Surface {
            AnimeStatusFilterMenu(
                selectedAnimeStatus = animeStatus,
                onAnimeStatusChanged = { status: AnimeStatus? ->
                    animeStatus = status
                }
            )
        }
    }
}
