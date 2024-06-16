package com.core.network.data.dto

import com.google.gson.annotations.SerializedName

data class ErrorResponseDto(

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)
