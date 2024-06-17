package com.core.network.data.dto

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(

	@field:SerializedName("data")
	val data: T? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)