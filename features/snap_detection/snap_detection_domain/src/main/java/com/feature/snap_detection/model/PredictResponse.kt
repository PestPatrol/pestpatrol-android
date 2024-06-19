package com.feature.snap_detection.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PredictResponse(
    val confidenceScore: Double,
    val disease: String,
    val cares: List<CaresItem?>,
    val mainCause: MainCause?,
    val impreciseTechniques: List<ImpreciseTechniquesItem?>
) : Parcelable

@Parcelize
data class CaresItem(
    val pictureLink: String? = null,
    val detail: String? = null,
    val title: String? = null
) : Parcelable

@Parcelize
data class ImpreciseTechniquesItem(
    val pictureLink: String? = null,
    val title: String? = null
) : Parcelable

@Parcelize
data class MainCause(
    val pictureLink: String? = null,
    val title: String? = null
) : Parcelable
