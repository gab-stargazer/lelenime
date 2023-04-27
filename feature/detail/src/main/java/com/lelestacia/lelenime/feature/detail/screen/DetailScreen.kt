package com.lelestacia.lelenime.feature.detail.screen

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.lelestacia.lelenime.core.common.R.string.unknown_error
import com.lelestacia.lelenime.core.common.Resource
import com.lelestacia.lelenime.core.common.theme.spacing
import com.lelestacia.lelenime.core.model.Anime
import com.lelestacia.lelenime.core.model.character.Character
import com.lelestacia.lelenime.feature.detail.component.AnimeGenres
import com.lelestacia.lelenime.feature.detail.component.AnimeHeader
import com.lelestacia.lelenime.feature.detail.component.AnimeInformation
import com.lelestacia.lelenime.feature.detail.component.AnimeSynopsis
import com.lelestacia.lelenime.feature.detail.component.CharacterImage

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedCrossfadeTargetStateParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavHostController,
    isDarkMode: Boolean,
    animeID: Int,
    animeResource: Resource<Anime>,
    charactersResource: Resource<List<Character>>,
    initiateView: (Int) -> Unit,
    updateAnimeByAnimeID: (Int) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val scrollState = rememberScrollState()
    LaunchedEffect(key1 = Unit) { initiateView(animeID) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Detail Anime",
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Navigation Icon"
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    scrolledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    titleContentColor =
                    if (isDarkMode) {
                        Color.White
                    } else {
                        Color.Black
                    },
                    navigationIconContentColor =
                    if (isDarkMode) {
                        Color.White
                    } else {
                        Color.Black
                    }
                ),
                scrollBehavior = scrollBehavior,
                actions = {
                    IconButton(onClick = { /*TODO: Implement Share Action*/ }) {
                        Icon(
                            imageVector = Icons.Filled.Share,
                            contentDescription = "Share this Anime"
                        )
                    }
                }
            )
        },
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { paddingValue ->
        animeResource.data?.let { anime ->
            Column(
                verticalArrangement = Arrangement.spacedBy(space = MaterialTheme.spacing.medium),
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(paddingValue)
                    .animateContentSize()
            ) {
                //  Header
                AnimeHeader(
                    malID = anime.malID,
                    coverImages = anime.coverImages,
                    title = anime.title,
                    titleJapanese = anime.titleJapanese,
                    rank = anime.rank,
                    score = anime.score,
                    scoredBy = anime.scoredBy,
                    status = anime.status
                )

                //  Favorite Button
                OutlinedButton(
                    onClick = { updateAnimeByAnimeID(animeID) },
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = MaterialTheme.spacing.large)
                ) {
                    Icon(
                        imageVector =
                        if (anime.isFavorite) {
                            Icons.Default.Favorite
                        } else {
                            Icons.Default.FavoriteBorder
                        },
                        contentDescription = "Favorite Icon"
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text =
                        if (anime.isFavorite) {
                            "Remove from favorite"
                        } else {
                            "Add to favorite"
                        }
                    )
                }

                //  Anime Information
                AnimeInformation(anime = anime)

                //  Anime Genres
                AnimeGenres(genres = anime.genres)

                //  Synopsis
                AnimeSynopsis(synopsis = anime.synopsis)

                //  Character

                when (val resource = charactersResource) {
                    is Resource.Error -> {
                        Column(
                            verticalArrangement = Arrangement.SpaceAround,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = MaterialTheme.spacing.large,
                                    vertical = MaterialTheme.spacing.extraLarge
                                )
                        ) {
                            Text(text = resource.message ?: stringResource(unknown_error))
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(onClick = { initiateView(animeID) }) {
                                Text(text = "Try Again")
                            }
                        }
                    }

                    Resource.Loading -> {
                        Column(
                            verticalArrangement = Arrangement.SpaceAround,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = MaterialTheme.spacing.large,
                                    vertical = MaterialTheme.spacing.extraLarge
                                )
                        ) {
                            Text(text = "Fetching Characters")
                            Spacer(modifier = Modifier.height(8.dp))
                            LinearProgressIndicator(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = MaterialTheme.spacing.large)
                            )
                        }
                    }

                    Resource.None -> Unit
                    is Resource.Success -> {
                        charactersResource.data?.let { characters ->
                            Column(
                                verticalArrangement = Arrangement.spacedBy(space = MaterialTheme.spacing.small)
                            ) {
                                Text(
                                    text = "Characters",
                                    style = MaterialTheme.typography.headlineSmall.copy(
                                        fontWeight = FontWeight.Bold
                                    ),
                                    modifier = Modifier.padding(
                                        horizontal = MaterialTheme.spacing.large,
                                        vertical = MaterialTheme.spacing.default
                                    )
                                )
                                LazyRow(
                                    contentPadding = PaddingValues(
                                        horizontal = MaterialTheme.spacing.large,
                                        vertical = MaterialTheme.spacing.default
                                    ),
                                    horizontalArrangement = Arrangement.spacedBy(space = MaterialTheme.spacing.extraSmall)
                                ) {
                                    items(
                                        items = characters,
                                        key = { character ->
                                            character.malID
                                        }
                                    ) { character ->
                                        CharacterImage(
                                            character = character,
                                            onCharacterClicked = { }
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(height = 16.dp))
                            }
                        }
                    }
                }

            }
        }
    }
}