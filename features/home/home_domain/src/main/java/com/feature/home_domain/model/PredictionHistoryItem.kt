package com.feature.home_domain.model

data class PredictionHistoryItem(
    val leafPictureLink: String,
    val createdAt: String,
    val confidenceScore: Double,
    val disease: String
)