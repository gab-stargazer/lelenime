package com.lelestacia.lelenime.feature.more.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreScreenButton(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Card(
        onClick = { onClick.invoke() },
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 24.dp
                )
            ) {
                Icon(imageVector = icon, contentDescription = "Icon $text")
                Text(
                    text = text,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Divider()
        }
    )
}
