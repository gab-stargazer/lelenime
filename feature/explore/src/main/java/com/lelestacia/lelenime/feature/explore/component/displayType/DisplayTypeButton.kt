package com.lelestacia.lelenime.feature.explore.component.displayType

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.lelestacia.lelenime.core.common.theme.LelenimeTheme
import java.util.Locale

/**
 * Composable function that represents a button for selecting a display type.
 *
 * @param isActive Whether the button is currently active (selected) or not.
 * @param displayType The [DisplayType] associated with the button.
 * @param icon The [ImageVector] representing the icon to display alongside the button.
 * @param onClicked The callback function that will be invoked when the button is clicked.
 *                 It takes a [DisplayType] as the parameter, indicating the selected display type.
 */
@Composable
fun DisplayTypeButton(
    isActive: Boolean,
    displayType: DisplayType,
    icon: ImageVector,
    onClicked: (DisplayType) -> Unit
) {
    Surface {
        AssistChip(
            onClick = {
                if (isActive) return@AssistChip
                onClicked(displayType)
            },
            label = {
                Text(
                    text = displayType.name
                        .lowercase()
                        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
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
                if (isActive) {
                    MaterialTheme.colorScheme.onPrimary
                } else {
                    MaterialTheme.colorScheme.onBackground
                },
                labelColor =
                if (isActive) {
                    MaterialTheme.colorScheme.onPrimary
                } else {
                    MaterialTheme.colorScheme.onBackground
                }
            ),
            border = AssistChipDefaults.assistChipBorder(
                borderColor =
                if (isActive) {
                    Color.Transparent
                } else {
                    MaterialTheme.colorScheme.outlineVariant
                }
            )
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO or UI_MODE_TYPE_NORMAL)
@Composable
fun PreviewDisplayTypeButtonActive() {
    LelenimeTheme {
        DisplayTypeButton(
            isActive = true,
            displayType = DisplayType.POPULAR,
            icon = Icons.Filled.Favorite
        ) {}
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL)
@Composable
fun PreviewDisplayTypeButtonActiveDarkMode() {
    LelenimeTheme {
        DisplayTypeButton(
            isActive = true,
            displayType = DisplayType.POPULAR,
            icon = Icons.Filled.Favorite
        ) {}
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO or UI_MODE_TYPE_NORMAL)
@Composable
fun PreviewDisplayTypeButtonInactive() {
    LelenimeTheme {
        DisplayTypeButton(
            isActive = false,
            displayType = DisplayType.POPULAR,
            icon = Icons.Filled.Favorite
        ) {}
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL)
@Composable
fun PreviewDisplayTypeButtonInactiveDarkMode() {
    LelenimeTheme {
        DisplayTypeButton(
            isActive = false,
            displayType = DisplayType.POPULAR,
            icon = Icons.Filled.Favorite
        ) {}
    }
}
