package com.lelestacia.lelenime.feature.detail.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.lelestacia.lelenime.core.model.Anime

@Composable
fun AnimeCoverImage(anime: Anime) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(data = anime.coverImages)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .crossfade(enable = true)
                .build(),
            contentDescription = "Cover Image from Anime ${anime.title}",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(225.dp)
                .blur(radius = 12.dp)
        )

        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(data = anime.coverImages)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .crossfade(enable = true)
                .build(),
            contentDescription = "Cover Image from Anime ${anime.title}",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(200.dp)
                .width(150.dp)
                .offset(y = 50.dp)
                .clip(RoundedCornerShape(8.dp))
        )
    }
}
