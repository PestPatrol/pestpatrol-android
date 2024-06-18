package com.feature.snap_detection_data.remote.dto

import com.google.gson.annotations.SerializedName

data class PredictResponseDto(

	@field:SerializedName("confidenceScore")
	val confidenceScore: Double? = null,

	@field:SerializedName("disease")
	val disease: String? = null
)
