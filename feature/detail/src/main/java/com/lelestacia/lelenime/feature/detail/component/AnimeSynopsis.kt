package com.lelestacia.lelenime.feature.detail.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.lelestacia.lelenime.core.common.theme.LelenimeTheme
import com.lelestacia.lelenime.core.common.theme.spacing
import com.lelestacia.lelenime.core.common.util.chainsawMan
import com.lelestacia.lelenime.feature.detail.R

@Composable
fun AnimeSynopsis(
    synopsis: String,
    onSynopsisOpen: (String) -> Unit
) {
    Text(
        text = stringResource(R.string.synopsis),
        style = MaterialTheme.typography.titleLarge.copy(
            fontWeight = FontWeight.Bold
        )
    )
    Text(
        text = synopsis,
        overflow = TextOverflow.Ellipsis,
        maxLines = 4,
        modifier = Modifier.fillMaxWidth()
    )
    TextButton(
        onClick = { onSynopsisOpen(synopsis) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = MaterialTheme.spacing.extraLarge)
    ) {
        Text(
            text = stringResource(R.string.read_more),
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline
        )
    }
}

@Preview
@Composable
fun PreviewAnimeSynopsisDayMode() {
    LelenimeTheme(
        darkTheme = false,
        dynamicColor = false
    ) {
        CardSection {
            AnimeSynopsis(
                synopsis = chainsawMan.synopsis as String,
                onSynopsisOpen = {}
            )
        }
    }
}

@Preview
@Composable
fun PreviewAnimeSynopsisDarkMode() {
    LelenimeTheme(
        darkTheme = true,
        dynamicColor = false
    ) {
        CardSection {
            AnimeSynopsis(
                synopsis = chainsawMan.synopsis as String,
                onSynopsisOpen = {}
            )
        }
    }
}
