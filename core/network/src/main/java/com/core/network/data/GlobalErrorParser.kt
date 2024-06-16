package com.core.network.data

import com.core.network.data.dto.ErrorResponseDto
import com.google.gson.Gson

class GlobalErrorParser(
    private val gson: Gson
) {

    fun parse(errorString: String?): String? {
        val errorMessage = gson.fromJson(errorString, ErrorResponseDto::class.java)
        return errorMessage.message
    }
}