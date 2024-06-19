package com.feature.snap_detection.repository

import android.graphics.Bitmap
import android.net.Uri
import com.feature.snap_detection.model.PredictResponse

interface SnapDetectionRepository {

    suspend fun predict(
        uri: Uri
    ): PredictResponse

    suspend fun predict(
        bitmap: Bitmap
    ): PredictResponse
}