package com.lelestacia.lelenime.core.common.lazyAnime

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.lelestacia.lelenime.core.common.R
import com.lelestacia.lelenime.core.common.displayStyle.DisplayStyle
import com.lelestacia.lelenime.core.common.itemAnime.AnimeCard
import com.lelestacia.lelenime.core.model.Anime

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyGridAnime(
    lazyGridState: LazyGridState,
    pagingAnime: LazyPagingItems<Anime>,
    displayStyle: DisplayStyle,
    modifier: Modifier = Modifier,
    onAnimeClicked: (Anime) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        state = lazyGridState,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(8.dp),
        modifier = modifier
            .fillMaxSize(),
        content = {
            items(
                count = pagingAnime.itemCount,
                key = { it }
            ) { index ->
                pagingAnime[index]?.let { anime ->
                    AnimeCard(
                        anime = anime,
                        displayStyle = displayStyle,
                        onAnimeClicked = { clickedAnime ->
                            onAnimeClicked(clickedAnime)
                        },
                        modifier = Modifier.animateItemPlacement()
                    )
                }
            }

            when (val appending = pagingAnime.loadState.append) {
                is LoadState.Error -> {
                    item(
                        span = {
                            GridItemSpan(3)
                        }
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 24.dp)
                        ) {
                            Text(
                                text = appending.error.message
                                    ?: stringResource(id = R.string.unknown_error)
                            )
                            Button(
                                onClick = { pagingAnime.retry() },
                                shape = RoundedCornerShape(4.dp)
                            ) {
                                Text(text = stringResource(id = R.string.retry))
                            }
                        }
                    }
                }

                LoadState.Loading -> {
                    item(
                        span = {
                            GridItemSpan(3)
                        }
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 24.dp)
                        ) {
                            CircularProgressIndicator(
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }

                is LoadState.NotLoading -> Unit
            }
        }
    )
}
