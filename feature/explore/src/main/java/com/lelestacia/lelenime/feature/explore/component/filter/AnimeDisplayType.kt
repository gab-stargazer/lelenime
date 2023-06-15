package com.lelestacia.lelenime.feature.explore.component.filter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.lelestacia.lelenime.core.common.R.string.displayed_anime
import com.lelestacia.lelenime.core.common.component.LelenimeFilterChip
import com.lelestacia.lelenime.core.common.theme.spacing
import com.lelestacia.lelenime.feature.explore.component.displayType.ExplorationDisplayType
import com.lelestacia.lelenime.feature.explore.component.displayType.LelenimeExplorationDisplayType
import com.lelestacia.lelenime.feature.explore.screen.DisplayType
import java.util.Locale

@Composable
@OptIn(ExperimentalLayoutApi::class)
fun AnimeDisplayTypeMenu(
    selectedDisplayType: DisplayType,
    onEvent: (DisplayType) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            MaterialTheme.spacing.extraSmall
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.large)
    ) {
        Text(
            text = stringResource(id = displayed_anime),
            style = MaterialTheme.typography.titleMedium
        )
        val allExplorationDisplayType: List<ExplorationDisplayType> =
            LelenimeExplorationDisplayType
        FlowRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                space = MaterialTheme.spacing.small
            ),
            maxItemsInEachRow = 2
        ) {
            allExplorationDisplayType.forEach { exploration ->
                val textToDisplay = exploration.displayType.name
                    .lowercase()
                    .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
                LelenimeFilterChip(
                    isActive = selectedDisplayType == exploration.displayType,
                    onClicked = {
                        onEvent(exploration.displayType)
                    },
                    label = {
                        Text(
                            text = textToDisplay,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.weight(1f)
                        )
                    },
                    trailingIcon = {},
                    leadingIcon = {},
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}
