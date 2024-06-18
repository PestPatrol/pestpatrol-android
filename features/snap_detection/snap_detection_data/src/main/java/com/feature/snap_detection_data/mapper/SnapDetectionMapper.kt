package com.feature.snap_detection_data.mapper

import com.feature.snap_detection.model.PredictResponse
import com.feature.snap_detection_data.remote.dto.PredictResponseDto

fun PredictResponseDto.toPredictResponse() = PredictResponse(
    confidenceScore = this.confidenceScore ?: -1.0,
    disease = this.disease.toString()
)