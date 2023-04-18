package com.lelestacia.lelenime.core.common

sealed class Resource<out T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(data: T?, message: String?) : Resource<T>(data, message)
    object Loading : Resource<Nothing>()
    object None : Resource<Nothing>()
}
