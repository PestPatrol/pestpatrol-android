package com.feature.auth_data.remote.dto

import com.google.gson.annotations.SerializedName

data class RegisterRequestDto(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("fullName")
	val fullName: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)
