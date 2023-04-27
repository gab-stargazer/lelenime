package com.lelestacia.lelenime.feature.detail.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lelestacia.lelenime.core.common.theme.LelenimeTheme
import com.lelestacia.lelenime.core.common.theme.spacing

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AnimeGenres(genres: List<String>) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.large)
            .clip(RoundedCornerShape(8.dp))
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(space = MaterialTheme.spacing.small),
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = MaterialTheme.spacing.medium)
        ) {
            Text(
                text = "Genre",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
                maxItemsInEachRow = 3
            ) {
                if (genres.isNotEmpty()) {
                    genres.forEach { genre ->
                        AssistChip(
                            onClick = { /*TODO*/ },
                            label = { Text(text = genre) }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewGenre() {
    LelenimeTheme {
        val genres = listOf("Action", "Drama", "Comedy")
        AnimeGenres(genres = genres)
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
fun PreviewGenreDarkMode() {
    LelenimeTheme {
        val genres = listOf("Action", "Drama", "Comedy")
        AnimeGenres(genres = genres)
    }
}