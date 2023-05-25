package com.lelestacia.lelenime.core.common.component

import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun LelenimeAssistiveChip(
    isActive: Boolean,
    text: @Composable () -> Unit,
    icon: @Composable () -> Unit,
    onClick: () -> Unit
) {
    Surface {
        AssistChip(
            onClick = onClick::invoke,
            label = {
                text()
            },
            leadingIcon = {
                icon()
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
