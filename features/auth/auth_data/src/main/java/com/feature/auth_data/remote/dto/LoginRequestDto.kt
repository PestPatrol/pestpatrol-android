package com.feature.auth_data.remote.dto

import com.google.gson.annotations.SerializedName

data class LoginRequestDto(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)
