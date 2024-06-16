package com.feature.auth_data.remote.dto

import com.google.gson.annotations.SerializedName

data class CheckTokenResponseDto(

	@field:SerializedName("data")
	val data: CheckTokenData? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class CheckTokenData(

	@field:SerializedName("userId")
	val userId: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)
