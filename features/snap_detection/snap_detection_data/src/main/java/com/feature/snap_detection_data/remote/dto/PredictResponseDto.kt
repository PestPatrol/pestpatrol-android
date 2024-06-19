package com.feature.snap_detection_data.remote.dto

import com.google.gson.annotations.SerializedName

data class CaresItemDto(

	@field:SerializedName("pictureLink")
	val pictureLink: String? = null,

	@field:SerializedName("detail")
	val detail: String? = null,

	@field:SerializedName("title")
	val title: String? = null
)

data class PredictResponseDto(

	@field:SerializedName("confidenceScore")
	val confidenceScore: Double? = null,

	@field:SerializedName("cares")
	val cares: List<CaresItemDto?>? = null,

	@field:SerializedName("disease")
	val disease: String? = null,

	@field:SerializedName("mainCause")
	val mainCause: MainCauseDto? = null,

	@field:SerializedName("impreciseTechniques")
	val impreciseTechniques: List<ImpreciseTechniquesItemDto?>? = null
)

data class ImpreciseTechniquesItemDto(

	@field:SerializedName("pictureLink")
	val pictureLink: String? = null,

	@field:SerializedName("title")
	val title: String? = null
)

data class MainCauseDto(

	@field:SerializedName("pictureLink")
	val pictureLink: String? = null,

	@field:SerializedName("title")
	val title: String? = null
)
