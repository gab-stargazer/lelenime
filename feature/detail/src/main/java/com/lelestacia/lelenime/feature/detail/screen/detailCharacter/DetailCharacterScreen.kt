package com.lelestacia.lelenime.feature.detail.screen.detailCharacter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.lelestacia.lelenime.core.common.theme.spacing
import com.lelestacia.lelenime.core.model.character.CharacterDetail

@Composable
fun DetailCharacterScreen(
    characterDetail: CharacterDetail
) {
    val context = LocalContext.current
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large),
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.large)
            .verticalScroll(rememberScrollState())
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large)) {
            AsyncImage(
                model =
                ImageRequest.Builder(context)
                    .data(characterDetail.images)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .memoryCacheKey(characterDetail.characterID.toString())
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .diskCacheKey(characterDetail.characterID.toString())
                    .allowHardware(false)
                    .allowRgb565(true)
                    .build(),
                contentDescription = "Image of ${characterDetail.name}",
                contentScale = ContentScale.Crop,
                filterQuality = FilterQuality.Medium,
                alignment = Alignment.TopCenter,
                modifier = Modifier
                    .width(125.dp)
                    .aspectRatio(3 / 4F)
                    .clip(RoundedCornerShape(4.dp))
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
            ) {
                Text(
                    text = characterDetail.name,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = characterDetail.characterKanjiName,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall)
                ) {
                    Text(
                        text = characterDetail.favoriteBy.toString(),
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "${characterDetail.name} is favorited by ${characterDetail.favoriteBy} people"
                    )
                }
            }
        }
        Text(
            text = characterDetail.characterInformation,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify
        )
    }
}
