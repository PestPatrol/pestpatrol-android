package com.feature.auth_data.remote.dto

import com.core.network.data.dto.ArticleResponse
import com.google.gson.annotations.SerializedName

data class RegisterResponseDto(

	@field:SerializedName("data")
	val data: RegisterData? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class RegisterUser(

	@field:SerializedName("reminders")
	val reminders: List<Any?>? = null,

	@field:SerializedName("favArticles")
	val favArticles: List<ArticleResponse?>? = null,

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("fullName")
	val fullName: String? = null,

	@field:SerializedName("userId")
	val userId: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("predictions")
	val predictions: List<Any?>? = null,

	@field:SerializedName("profpicLink")
	val profPicLink: String? = null
)

data class RegisterData(

	@field:SerializedName("user")
	val user: RegisterUser? = null
)