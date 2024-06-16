package com.feature.auth_data.remote.dto

import com.google.gson.annotations.SerializedName

data class LoginResponseDto(

	@field:SerializedName("data")
	val data: LoginData? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class LoginData(

	@field:SerializedName("token")
	val token: String? = null
)
