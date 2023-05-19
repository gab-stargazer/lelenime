package com.lelestacia.lelenime.core.common.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LelenimeFilterChip(
    isActive: Boolean,
    label: @Composable () -> Unit,
    leadingIcon: @Composable () -> Unit,
    onClick: () -> Unit
) {
    FilterChip(
        selected = isActive,
        onClick = { onClick.invoke() },
        label = {
            label()
        },
        leadingIcon = {
            leadingIcon()
        },
        colors = FilterChipDefaults.filterChipColors(
            containerColor =
            if (isActive) {
                MaterialTheme.colorScheme.primary
            } else {
                Color.Transparent
            },
            iconColor =
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
        border = FilterChipDefaults.filterChipBorder(
            borderColor =
            if (isActive) {
                Color.Transparent
            } else {
                MaterialTheme.colorScheme.outlineVariant
            }
        )
    )
}
