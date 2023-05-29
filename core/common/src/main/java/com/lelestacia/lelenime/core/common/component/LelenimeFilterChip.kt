package com.lelestacia.lelenime.core.common.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LelenimeFilterChip(
    isActive: Boolean,
    label: @Composable () -> Unit,
    leadingIcon: @Composable () -> Unit,
    trailingIcon: @Composable () -> Unit,
    onClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    FilterChip(
        selected = isActive,
        onClick = { onClicked.invoke() },
        label = {
            label()
        },
        leadingIcon = {
            leadingIcon()
        },
        trailingIcon = {
            trailingIcon()
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
        ),
        modifier = modifier
    )
}
