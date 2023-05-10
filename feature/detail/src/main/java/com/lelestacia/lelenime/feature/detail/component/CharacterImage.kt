package com.lelestacia.lelenime.feature.detail.component

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.lelestacia.lelenime.core.model.character.Character

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterImage(
    character: Character,
    onCharacterClicked: (Int) -> Unit
) {
    Card(
        onClick = { onCharacterClicked(character.malID) }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(data = character.images)
                .memoryCachePolicy(policy = CachePolicy.ENABLED)
                .memoryCacheKey(key = character.malID.toString())
                .diskCachePolicy(policy = CachePolicy.ENABLED)
                .diskCacheKey(key = character.malID.toString())
                .allowHardware(enable = false)
                .allowRgb565(enable = true)
                .crossfade(enable = true)
                .build(),
            contentDescription = "Image of ${character.name}",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(width = 100.dp)
                .aspectRatio(ratio = 3f / 4f)
                .clip(shape = RoundedCornerShape(size = 8.dp))
                .testTag(character.name)
        )
    }
}
