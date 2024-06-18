package com.feature.snap_detection.model

data class PredictResponse(
    val confidenceScore: Double,
    val disease: String
)
