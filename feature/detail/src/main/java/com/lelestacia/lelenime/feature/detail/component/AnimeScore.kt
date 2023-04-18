package com.lelestacia.lelenime.feature.detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun AnimeScore(score: String, scoredBy: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = score,
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.SemiBold
            )
        )
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = stringResource(id = com.lelestacia.lelenime.core.common.R.string.score_icon)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = scoredBy,
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.SemiBold
            )
        )
        Icon(
            imageVector = Icons.Filled.People,
            contentDescription = stringResource(id = com.lelestacia.lelenime.core.common.R.string.scoredBy_icon)
        )
    }
}
