package com.lelestacia.lelenime.feature.explore.component.header

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.lelestacia.lelenime.feature.explore.screen.DisplayType

@Composable
fun DisplayTypeButton(
    isActive: Boolean,
    displayType: DisplayType,
    icon: ImageVector,
    onClicked: (DisplayType) -> Unit
) {
    AssistChip(
        onClick = {
            if (isActive) return@AssistChip
            onClicked(displayType)
        },
        label = {
            Text(
                text = displayType.name,
                color =
                if (isSystemInDarkTheme()) {
                    if (isActive) {
                        Color.Black
                    } else {
                        Color.White
                    }
                } else {
                    if (isActive) {
                        Color.White
                    } else {
                        Color.Black
                    }
                }
            )
        },
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
        },
        colors = AssistChipDefaults.assistChipColors(
            containerColor =
            if (isActive) {
                MaterialTheme.colorScheme.primary
            } else {
                Color.Transparent
            },
            leadingIconContentColor =
            if (isSystemInDarkTheme()) {
                if (isActive) {
                    Color.Black
                } else {
                    MaterialTheme.colorScheme.primary
                }
            } else {
                if (isActive) {
                    Color.White
                } else {
                    MaterialTheme.colorScheme.primary
                }
            }
        ),
        border = AssistChipDefaults.assistChipBorder(
            borderColor =
            if (isActive) {
                Color.Transparent
            } else {
                MaterialTheme.colorScheme.outline
            }
        )
    )
}

@Preview(uiMode = UI_MODE_NIGHT_NO or UI_MODE_TYPE_NORMAL)
@Composable
fun PreviewDisplayTypeButtonActive() {
    DisplayTypeButton(
        isActive = true,
        displayType = DisplayType.POPULAR,
        icon = Icons.Filled.Favorite,
        onClicked = {}
    )
}

@Preview(uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL)
@Composable
fun PreviewDisplayTypeButtonActiveDarkMode() {
    DisplayTypeButton(
        isActive = true,
        displayType = DisplayType.POPULAR,
        icon = Icons.Filled.Favorite,
        onClicked = {}
    )
}

@Preview(uiMode = UI_MODE_NIGHT_NO or UI_MODE_TYPE_NORMAL)
@Composable
fun PreviewDisplayTypeButtonInactive() {
    DisplayTypeButton(
        isActive = false,
        displayType = DisplayType.POPULAR,
        icon = Icons.Filled.Favorite,
        onClicked = {}
    )
}

@Preview(uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL)
@Composable
fun PreviewDisplayTypeButtonInactiveDarkMode() {
    DisplayTypeButton(
        isActive = false,
        displayType = DisplayType.POPULAR,
        icon = Icons.Filled.Favorite,
        onClicked = {}
    )
}
