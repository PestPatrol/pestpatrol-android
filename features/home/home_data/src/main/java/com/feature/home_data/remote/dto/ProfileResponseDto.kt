package com.feature.home_data.remote.dto

import com.google.gson.annotations.SerializedName

data class ProfileResponseDto(

	@field:SerializedName("fullName")
	val fullName: String? = null,

	@field:SerializedName("userId")
	val userId: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("profpicLink")
	val profPicLink: String? = null
)
