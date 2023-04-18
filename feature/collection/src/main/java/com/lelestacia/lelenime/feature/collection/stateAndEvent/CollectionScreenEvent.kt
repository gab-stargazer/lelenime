package com.lelestacia.lelenime.feature.collection.stateAndEvent

import com.lelestacia.lelenime.core.common.displayStyle.DisplayStyle

sealed class CollectionScreenEvent {
    object OnDisplayStyleOptionMenuChangedState : CollectionScreenEvent()
    data class OnDisplayStyleChanged(val selectedStyle: DisplayStyle) : CollectionScreenEvent()
}
