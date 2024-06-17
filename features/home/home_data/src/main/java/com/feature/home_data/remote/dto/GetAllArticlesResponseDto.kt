package com.feature.home_data.remote.dto

import com.google.gson.annotations.SerializedName

data class GetAllArticlesResponseDto(

	@field:SerializedName("data")
	val data: List<GetAllArticlesDataItem?>? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class GetAllArticlesCreatedAt(

	@field:SerializedName("_nanoseconds")
	val nanoseconds: Int? = null,

	@field:SerializedName("_seconds")
	val seconds: Int? = null
)

data class GetAllArticlesDataItem(

	@field:SerializedName("data")
	val data: GetAllArticlesData? = null,

	@field:SerializedName("articleId")
	val articleId: String? = null
)

data class GetAllArticlesData(

	@field:SerializedName("createdAt")
	val createdAt: GetAllArticlesCreatedAt? = null,

	@field:SerializedName("pictureLink")
	val pictureLink: String? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("content")
	val content: String? = null
)
