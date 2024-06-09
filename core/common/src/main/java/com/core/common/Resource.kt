package com.core.common

typealias SimpleResource = Resource<Unit>

sealed class Resource<T>(val data: T? = null, val uiText: UiText? = null) {
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(uiText: UiText? = null, data: T? = null) : Resource<T>(data, uiText)
    class Success<T>(data: T?) : Resource<T>(data)
}