package com.lelestacia.lelenime.core.common.lazyAnime

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.lelestacia.lelenime.core.common.R
import com.lelestacia.lelenime.core.common.itemAnime.AnimeList
import com.lelestacia.lelenime.core.model.Anime

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyListAnime(
    lazyListState: LazyListState,
    pagingAnime: LazyPagingItems<Anime>,
    onAnimeClicked: (Anime) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        state = lazyListState,
        modifier = modifier
    ) {
        items(items = pagingAnime) { pagingAnime ->
            pagingAnime?.let { anime ->
                AnimeList(
                    anime = anime,
                    onAnimeClicked = onAnimeClicked,
                    modifier = Modifier.animateItemPlacement()
                )
                Divider()
            }
        }

        when (val appending = pagingAnime.loadState.append) {
            is LoadState.Error -> {
                item {
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
                item {
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
}
