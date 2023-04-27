package com.lelestacia.lelenime.feature.detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lelestacia.lelenime.core.common.theme.spacing
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
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier
            .padding(horizontal = MaterialTheme.spacing.large)
            .clip(RoundedCornerShape(8.dp))
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(space = MaterialTheme.spacing.small),
            modifier = Modifier.padding(all = MaterialTheme.spacing.medium)
        ) {
            Text(
                text = "Information",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )

            //  Type
            Row {
                Text(text = stringResource(id = R.string.type))
                Text(
                    text = anime.type,
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(1f)
                )
            }
            Divider(color = MaterialTheme.colorScheme.onSurfaceVariant)

            //  Episodes
            Row {
                Text(text = stringResource(id = R.string.episode))
                Text(
                    text = (anime.episodes ?: 0).toString(),
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(1f)
                )
            }
            Divider(color = MaterialTheme.colorScheme.onSurfaceVariant)

            //  Season
            anime.season?.let { unformattedSeason ->
                val season = "${
                    unformattedSeason.replaceFirstChar {
                        it.titlecase()
                    }
                } ${anime.year}"
                Row {
                    Text(text = stringResource(id = R.string.season))
                    Text(
                        text = season,
                        textAlign = TextAlign.End,
                        modifier = Modifier.weight(1f)
                    )
                }
                Divider(color = MaterialTheme.colorScheme.onSurfaceVariant)
            }

            //  Airing Information
            anime.startedDate?.let { startedDate ->
                val airingPeriod =
                    if (anime.finishedDate.isNullOrEmpty()) {
                        dateParser(startedDate)
                    } else {
                        "${dateParser(startedDate)} - ${dateParser(anime.finishedDate)}"
                    }
                Row {
                    Text(text = stringResource(id = R.string.aired))
                    Text(
                        text = airingPeriod,
                        textAlign = TextAlign.End,
                        modifier = Modifier.weight(1f)
                    )
                }
                Divider(color = MaterialTheme.colorScheme.onSurfaceVariant)
            }

            //  Studio
            if (anime.studios.isNotEmpty()) {
                val studios = listToString(anime.studios)
                Row {
                    Text(text = stringResource(id = R.string.studio))
                    Text(
                        text = studios,
                        textAlign = TextAlign.End,
                        modifier = Modifier.weight(1f)
                    )
                }
                Divider(color = MaterialTheme.colorScheme.onSurfaceVariant)
            }

            //  Duration
            Row {
                Text(text = stringResource(id = R.string.duration))
                Text(
                    text = anime.duration,
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(1f)
                )
            }
            Divider(color = MaterialTheme.colorScheme.onSurfaceVariant)

            //  Rating
            Row {
                Text(text = stringResource(id = R.string.rating))
                Text(
                    text = anime.rating,
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}
