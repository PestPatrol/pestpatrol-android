package com.feature.home_domain.model

import androidx.compose.runtime.Immutable
import java.util.UUID

@Immutable
data class PredictionHistoryItem(
    val leafPictureLink: String,
    val createdAt: String,
    val confidenceScore: Double,
    val disease: String
) {
    val id = UUID.randomUUID().toString()
}