package com.lelestacia.lelenime.feature.explore.component.filter

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.lelestacia.lelenime.core.common.theme.spacing
import com.lelestacia.lelenime.feature.explore.stateAndEvent.UpcomingAnimeFilter
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpcomingAnimeFilterSection(
    upcomingAnimeFilter: UpcomingAnimeFilter,
    onUpcomingAnimeFilterChanged: (UpcomingAnimeFilter) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(start = MaterialTheme.spacing.large)
    ) {
        UpcomingAnimeFilterChip(
            upcomingAnimeFilter = upcomingAnimeFilter,
            onUpcomingAnimeFilterChanged = onUpcomingAnimeFilterChanged
        )
        if (upcomingAnimeFilter.animeType != null) {
            FilterChip(
                selected = true,
                onClick = {},
                label = {
                    Text(
                        text = upcomingAnimeFilter.animeType.name
                            .lowercase()
                            .replaceFirstChar {
                                if (it.isLowerCase()) {
                                    it.titlecase(
                                        Locale.ROOT
                                    )
                                } else {
                                    it.toString()
                                }
                            }
                    )
                },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    iconColor = MaterialTheme.colorScheme.onPrimary,
                    labelColor = MaterialTheme.colorScheme.onPrimary
                ),
                border = FilterChipDefaults.filterChipBorder(
                    borderColor = Color.Transparent
                )
            )
        }
    }
}
