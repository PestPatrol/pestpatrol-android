package com.feature.home_data.remote.dto

import com.google.gson.annotations.SerializedName

data class PredictionHistoryResponseDto(

	@field:SerializedName("leafPictureLink")
	val leafPictureLink: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("confidenceScore")
	val confidenceScore: Double? = null,

	@field:SerializedName("disease")
	val disease: String? = null
)
