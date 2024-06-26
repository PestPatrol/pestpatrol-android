package com.core.common.util

import androidx.annotation.StringRes
import com.core.common.R

sealed class UiText {
    data class DynamicString(val value: String) : UiText()
    data class StringResource(@StringRes val id: Int) : UiText()

    companion object {
        fun networkError(): UiText {
            return UiText.StringResource(R.string.error_network_connection)
        }

        fun unknownError(): UiText {
            return UiText.StringResource(R.string.error_uknown)
        }
    }
}