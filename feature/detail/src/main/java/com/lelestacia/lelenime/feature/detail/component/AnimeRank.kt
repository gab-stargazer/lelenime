package com.lelestacia.lelenime.feature.detail.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight

@Composable
fun AnimeRank(rank: String) {
    Text(
        text = stringResource(id = com.lelestacia.lelenime.core.common.R.string.rank),
        style = MaterialTheme.typography.headlineSmall.copy(
            fontWeight = FontWeight.Black
        )
    )
}
