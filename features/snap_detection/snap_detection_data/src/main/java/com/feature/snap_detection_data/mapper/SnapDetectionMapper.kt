package com.feature.snap_detection_data.mapper

import com.feature.snap_detection.model.CaresItem
import com.feature.snap_detection.model.ImpreciseTechniquesItem
import com.feature.snap_detection.model.MainCause
import com.feature.snap_detection.model.PredictResponse
import com.feature.snap_detection_data.remote.dto.PredictResponseDto

fun PredictResponseDto.toPredictResponse() = PredictResponse(
    confidenceScore = this.confidenceScore ?: -1.0,
    disease = this.disease.toString(),
    cares = this.cares?.map {
        CaresItem(
            pictureLink = it?.pictureLink.toString(),
            detail = it?.detail,
            title = it?.title
        )
    } ?: emptyList(),
    mainCause = MainCause(
        pictureLink = this.mainCause?.pictureLink,
        title = this.mainCause?.title
    ),
    impreciseTechniques = this.impreciseTechniques?.map {
        ImpreciseTechniquesItem(
            pictureLink = it?.pictureLink,
            title = it?.title
        )
    } ?: emptyList()
)