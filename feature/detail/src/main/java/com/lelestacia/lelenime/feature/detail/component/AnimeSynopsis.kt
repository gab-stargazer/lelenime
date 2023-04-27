package com.lelestacia.lelenime.feature.detail.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.lelestacia.lelenime.core.common.theme.spacing

@Composable
fun AnimeSynopsis(synopsis: String?) {
    var openDialog by remember {
        mutableStateOf(false)
    }
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
                text = "Synopsis",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )

            if (synopsis.isNullOrEmpty()) {
                Text(
                    text = stringResource(id = com.lelestacia.lelenime.core.common.R.string.empty_synopsis),
                    modifier = Modifier.fillMaxWidth()
                )
                return@Surface
            }

            Text(
                text = synopsis,
                overflow = TextOverflow.Ellipsis,
                maxLines = 4,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "Read More",
                color = Color.White.copy(
                    blue = 0.75F
                ),
                textAlign = TextAlign.Center,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = MaterialTheme.spacing.small)
                    .clickable { openDialog = true }
            )

            if (openDialog) {
                Dialog(onDismissRequest = { openDialog = false }) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = MaterialTheme.spacing.extraLarge),
                        shape = MaterialTheme.shapes.large
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
                            modifier = Modifier
                                .padding(MaterialTheme.spacing.large)
                                .verticalScroll(rememberScrollState())
                        ) {
                            Text(
                                text = "Synopsis",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Text(
                                text = synopsis,
                                textAlign = TextAlign.Justify
                            )
                        }
                    }
                }
            }
        }
    }
}