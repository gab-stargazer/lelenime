package com.lelestacia.lelenime.feature.detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lelestacia.lelenime.core.model.Anime
import com.lelestacia.lelenime.feature.detail.R
import com.lelestacia.lelenime.feature.detail.util.DateParser
import com.lelestacia.lelenime.feature.detail.util.ListToString

@Composable
fun AnimeInformation(
    anime: Anime,
    dateParser: DateParser = DateParser(),
    listToString: ListToString = ListToString()
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(end = 8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.type),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(id = R.string.episode),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(id = R.string.season),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(id = R.string.aired),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(id = R.string.studio),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(id = R.string.source),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(id = R.string.genre),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(id = R.string.duration),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(id = R.string.rating),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(
                    id = R.string.information_value,
                    anime.type
                ),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Normal
            )
            Text(
                text = stringResource(
                    id = R.string.information_value,
                    anime.episodes ?: "Unknown"
                ),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Normal
            )
            val season =
                if (anime.season.isNullOrEmpty()) {
                    "Unknown"
                } else {
                    "${
                    anime.season?.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase() else it.toString()
                    }
                    } ${anime.year}"
                }
            Text(
                text = stringResource(id = R.string.information_value, season),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Normal
            )
            Text(
                text = stringResource(
                    id = R.string.information_value,
                    "${dateParser.invoke(anime.startedDate)} - ${dateParser.invoke(anime.finishedDate)}"
                ),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Normal
            )
            Text(
                text = stringResource(
                    id = R.string.information_value,
                    listToString.invoke(anime.studios)
                ),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Normal
            )
            Text(
                text = stringResource(id = R.string.information_value, anime.source),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Normal
            )
            Text(
                text = stringResource(
                    id = R.string.information_value,
                    listToString.invoke(anime.genres)
                ),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Normal
            )
            Text(
                text = stringResource(id = R.string.information_value, anime.duration),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Normal
            )
            Text(
                text = stringResource(id = R.string.information_value, anime.rating),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Normal
            )
        }
    }
}
