package com.lelestacia.lelenime.core.common.displayStyle

import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.lelestacia.lelenime.core.common.R

@Composable
fun DisplayStyleMenu(
    currentStyle: DisplayStyle,
    isExpanded: Boolean,
    onStyleChanged: (DisplayStyle) -> Unit,
    onDismiss: () -> Unit
) {
    DropdownMenu(
        expanded = isExpanded,
        onDismissRequest = { onDismiss.invoke() }
    ) {
        DropdownMenuItem(
            text = {
                Text(text = stringResource(id = R.string.card))
            },
            onClick = {
                onDismiss.invoke()
                onStyleChanged(DisplayStyle.CARD)
            },
            trailingIcon = {
                RadioButton(
                    selected = currentStyle == DisplayStyle.CARD,
                    onClick = {
                        onDismiss.invoke()
                        onStyleChanged(DisplayStyle.CARD)
                    }
                )
            }
        )
        Divider()
        DropdownMenuItem(
            text = {
                Text(text = stringResource(id = R.string.compact_card))
            },
            onClick = {
                onDismiss.invoke()
                onStyleChanged(DisplayStyle.COMPACT_CARD)
            },
            trailingIcon = {
                RadioButton(
                    selected = currentStyle == DisplayStyle.COMPACT_CARD,
                    onClick = {
                        onDismiss.invoke()
                        onStyleChanged(DisplayStyle.COMPACT_CARD)
                    }
                )
            }
        )
        Divider()
        DropdownMenuItem(
            text = {
                Text(text = stringResource(id = R.string.list))
            },
            onClick = {
                onDismiss.invoke()
                onStyleChanged(DisplayStyle.LIST)
            },
            trailingIcon = {
                RadioButton(
                    selected = currentStyle == DisplayStyle.LIST,
                    onClick = {
                        onDismiss.invoke()
                        onStyleChanged(DisplayStyle.LIST)
                    }
                )
            }
        )
    }
}
