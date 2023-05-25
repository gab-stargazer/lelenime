package com.lelestacia.lelenime.feature.explore.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lelestacia.lelenime.core.common.component.LelenimeAssistiveChip
import com.lelestacia.lelenime.core.common.component.LelenimeFilterChip
import com.lelestacia.lelenime.core.common.theme.spacing
import com.lelestacia.lelenime.feature.explore.R
import com.lelestacia.lelenime.feature.explore.component.filter.PopularAnimeFilterMenuItem
import com.lelestacia.lelenime.feature.explore.component.filter.UpcomingAnimeFilterMenuItem
import com.lelestacia.lelenime.feature.explore.screen.DisplayType
import com.lelestacia.lelenime.feature.explore.stateAndEvent.AnimeFilter
import java.util.Locale

@Composable
fun AnimeFilterSection(
    displayType: DisplayType,
    appliedAnimeFilter: AnimeFilter,
    currentAnimeFilter: AnimeFilter,
    onAnimeFilterChanged: (AnimeFilter) -> Unit,
    onFilterApplied: () -> Unit
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
    val isActive =
        when (displayType) {
            DisplayType.POPULAR -> {
                val popularAnimeFilter = currentAnimeFilter.popularAnimeFilter
                currentAnimeFilter.popularAnimeFilter.animeStatus != null || popularAnimeFilter.animeType != null
            }

            DisplayType.AIRING -> false
            DisplayType.UPCOMING -> currentAnimeFilter.upcomingAnimeFilter.animeType != null
            DisplayType.SEARCH -> false
        }
    AnimatedVisibility(
        visible = displayType != DisplayType.AIRING,
        enter = fadeIn() + slideInVertically(tween()),
        exit = fadeOut() + slideOutVertically(tween())
    ) {
        Surface {
            Row(
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.medium)
                    .animateContentSize(),
                horizontalArrangement = Arrangement.spacedBy(
                    space = MaterialTheme.spacing.small
                )
            ) {
                AnimatedVisibility(
                    visible = when (displayType) {
                        DisplayType.POPULAR -> appliedAnimeFilter.popularAnimeFilter != currentAnimeFilter.popularAnimeFilter
                        DisplayType.AIRING -> false
                        DisplayType.UPCOMING -> appliedAnimeFilter.upcomingAnimeFilter != currentAnimeFilter.upcomingAnimeFilter
                        DisplayType.SEARCH -> false
                    },
                    enter = fadeIn(tween()) + slideInHorizontally(tween()),
                    exit = fadeOut(tween()) + slideOutHorizontally(tween())
                ) {
                    LelenimeAssistiveChip(
                        isActive = true,
                        text = { Text(text = "Apply") },
                        icon = {},
                        onClick = onFilterApplied::invoke
                    )
                }

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
                    when (displayType) {
                        DisplayType.POPULAR -> PopularAnimeFilterMenuItem(
                            popularAnimeFilter = currentAnimeFilter.popularAnimeFilter,
                            onPopularAnimeFilterChanged = {
                                onAnimeFilterChanged(
                                    currentAnimeFilter.copy(
                                        popularAnimeFilter = it
                                    )
                                )
                            },
                            isAnimeTypeMenuOpened = isAnimeTypeMenuOpened,
                            onAnimeTypeMenuStateChanged = {
                                isAnimeTypeMenuOpened = it
                            },
                            isAnimeStatusMenuOpened = isAnimeStatusMenuOpened,
                            onAnimeStatusMenuStateChanged = {
                                isAnimeStatusMenuOpened = it
                            }
                        )

                        DisplayType.AIRING -> Unit
                        DisplayType.UPCOMING -> UpcomingAnimeFilterMenuItem(
                            upcomingAnimeFilter = currentAnimeFilter.upcomingAnimeFilter,
                            onUpcomingAnimeFilterChanged = {
                                onAnimeFilterChanged(
                                    currentAnimeFilter.copy(
                                        upcomingAnimeFilter = it
                                    )
                                )
                            },
                            isAnimeTypeMenuOpened = isAnimeTypeMenuOpened,
                            onAnimeTypeMenuStateChanged = {
                                isAnimeTypeMenuOpened = it
                            }
                        )

                        DisplayType.SEARCH -> Unit
                    }
                }

                when (displayType) {
                    DisplayType.POPULAR -> {
                        currentAnimeFilter.popularAnimeFilter.animeType?.let {
                            LelenimeFilterChip(
                                isActive = true,
                                label = {
                                    Text(
                                        text = it.name
                                            .lowercase()
                                            .replaceFirstChar {
                                                if (it.isLowerCase()) {
                                                    it.titlecase(Locale.ROOT)
                                                } else {
                                                    it.toString()
                                                }
                                            }
                                    )
                                },
                                leadingIcon = {},
                                trailingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = null,
                                        modifier = Modifier.size(16.dp)
                                    )
                                },
                                onClicked = {
                                    onAnimeFilterChanged(
                                        currentAnimeFilter.copy(
                                            popularAnimeFilter = currentAnimeFilter
                                                .popularAnimeFilter.copy(
                                                    animeType = null
                                                )
                                        )
                                    )
                                }
                            )
                        }

                        currentAnimeFilter.popularAnimeFilter.animeStatus?.let {
                            LelenimeFilterChip(
                                isActive = true,
                                label = {
                                    Text(
                                        text = it.name
                                            .lowercase()
                                            .replaceFirstChar {
                                                if (it.isLowerCase()) {
                                                    it.titlecase(Locale.ROOT)
                                                } else {
                                                    it.toString()
                                                }
                                            }
                                    )
                                },
                                leadingIcon = {},
                                trailingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = null,
                                        modifier = Modifier.size(16.dp)
                                    )
                                },
                                onClicked = {
                                    onAnimeFilterChanged(
                                        currentAnimeFilter.copy(
                                            popularAnimeFilter = currentAnimeFilter
                                                .popularAnimeFilter.copy(
                                                    animeStatus = null
                                                )
                                        )
                                    )
                                }
                            )
                        }
                    }

                    DisplayType.AIRING -> Unit
                    DisplayType.UPCOMING -> {
                        currentAnimeFilter.upcomingAnimeFilter.animeType?.let {
                            LelenimeFilterChip(
                                isActive = true,
                                label = {
                                    Text(
                                        text = it.name
                                            .lowercase()
                                            .replaceFirstChar {
                                                if (it.isLowerCase()) {
                                                    it.titlecase(Locale.ROOT)
                                                } else {
                                                    it.toString()
                                                }
                                            }
                                    )
                                },
                                leadingIcon = {},
                                trailingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = null,
                                        modifier = Modifier.size(16.dp)
                                    )
                                },
                                onClicked = {
                                    onAnimeFilterChanged(
                                        currentAnimeFilter.copy(
                                            upcomingAnimeFilter = currentAnimeFilter
                                                .upcomingAnimeFilter.copy(
                                                    animeType = null
                                                )
                                        )
                                    )
                                }
                            )
                        }
                    }

                    DisplayType.SEARCH -> Unit
                }
            }
        }
    }
}
