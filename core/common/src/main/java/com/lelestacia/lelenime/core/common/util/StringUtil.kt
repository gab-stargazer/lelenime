package com.lelestacia.lelenime.core.common.util

fun String?.isNotNullOrEmpty(): Boolean {
    return !this.isNullOrEmpty()
}