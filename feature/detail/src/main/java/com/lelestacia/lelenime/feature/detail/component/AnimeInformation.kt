package com.lelestacia.lelenime.feature.detail.component

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.lelestacia.lelenime.core.common.theme.LelenimeTheme
import com.lelestacia.lelenime.core.common.util.chainsawMan
import com.lelestacia.lelenime.core.common.util.isNotNull
import com.lelestacia.lelenime.core.common.util.isNotNullOrEmpty
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
    Text(
        text = stringResource(R.string.information),
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
    if (anime.episodes.isNotNull()) {
        Row {
            Text(text = stringResource(id = R.string.episode))
            Text(
                text = (anime.episodes as Int).toString(),
                textAlign = TextAlign.End,
                modifier = Modifier.weight(1f)
            )
        }
        Divider(color = MaterialTheme.colorScheme.onSurfaceVariant)
    }

    //  Season & Year
    if (anime.season.isNotNullOrEmpty()) {
        val season = (anime.season as String)
            .replaceFirstChar {
                it.titlecase()
            }
        val seasonWithYear = "$season ${anime.year}"

        Row {
            Text(text = stringResource(id = R.string.season))
            Text(
                text = seasonWithYear,
                textAlign = TextAlign.End,
                modifier = Modifier.weight(1f)
            )
        }
        Divider(color = MaterialTheme.colorScheme.onSurfaceVariant)
    }

    //  Airing Information
    if (anime.startedDate.isNotNullOrEmpty()) {
        val airingPeriod =
            if (anime.finishedDate.isNullOrEmpty()) {
                dateParser(anime.startedDate as String)
            } else {
                val startedDate = dateParser(anime.startedDate as String)
                val finishedDate = dateParser(anime.finishedDate)
                "$startedDate - $finishedDate"
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

@Preview
@Composable
fun PreviewAnimeInformationDayMode() {
    LelenimeTheme(
        darkTheme = false,
        dynamicColor = false
    ) {
        CardSection {
            AnimeInformation(
                anime = chainsawMan
            )
        }
    }
}

@Preview
@Composable
fun PreviewAnimeInformationDarkMode() {
    LelenimeTheme(
        darkTheme = true,
        dynamicColor = false
    ) {
        CardSection {
            AnimeInformation(
                anime = chainsawMan
            )
        }
    }
}
