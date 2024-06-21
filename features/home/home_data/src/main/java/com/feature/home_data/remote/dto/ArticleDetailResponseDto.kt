package com.feature.home_data.remote.dto

import com.google.gson.annotations.SerializedName

data class ArticleDetailResponseDto(

	@field:SerializedName("createdAt")
	val createdAt: CreatedAt? = null,

	@field:SerializedName("articleId")
	val articleId: String? = null,

	@field:SerializedName("pictureLink")
	val pictureLink: String? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("content")
	val content: String? = null
)

data class CreatedAt(

	@field:SerializedName("_nanoseconds")
	val nanoseconds: Int? = null,

	@field:SerializedName("_seconds")
	val seconds: Int? = null
)
