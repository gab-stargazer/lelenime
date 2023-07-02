package com.lelestacia.lelenime.core.common.itemAnime

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.lelestacia.lelenime.core.common.R
import com.lelestacia.lelenime.core.model.Anime

@Composable
fun AnimeList(
    anime: Anime,
    onAnimeClicked: (Anime) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp))
            .clickable { onAnimeClicked(anime) }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(data = anime.coverImages)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .memoryCacheKey(anime.malID.toString())
                .diskCachePolicy(CachePolicy.ENABLED)
                .diskCacheKey(anime.malID.toString())
                .allowHardware(false)
                .allowRgb565(true)
                .crossfade(enable = true)
                .build(),
            contentDescription = "Cover Image from Anime ${anime.title}",
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.colorMatrix(
                colorMatrix = ColorMatrix().apply {
                    setToSaturation(sat = 0.85F)
                }
            ),
            filterQuality = FilterQuality.Medium,
            modifier = Modifier
                .width(100.dp)
                .height(134.dp)
                .clip(RoundedCornerShape(8.dp))
                .clickable {
                    onAnimeClicked(anime)
                }
        )
        Column(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 8.dp
                )
        ) {
            Text(
                text = anime.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Text(
                        text = "Score",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Episode",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Status",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(
                                id = R.string.information_value,
                                (anime.score ?: 0)
                            ),
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.SemiBold
                        )
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Star Icon",
                            modifier = Modifier
                                .padding(start = 2.dp)
                                .size(16.dp)
                        )
                    }
                    Text(
                        text =
                        if (anime.episodes != null) {
                            stringResource(
                                id = R.string.information_value,
                                anime.episodes.toString()
                            )
                        } else {
                            stringResource(id = R.string.information_value, "Unknown")
                        },
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = stringResource(id = R.string.information_value, anime.status),
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}
