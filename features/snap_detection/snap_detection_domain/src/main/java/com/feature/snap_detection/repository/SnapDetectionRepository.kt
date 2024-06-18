package com.feature.snap_detection.repository

import android.net.Uri
import com.feature.snap_detection.model.PredictResponse

interface SnapDetectionRepository {

    suspend fun predict(
        uri: Uri
    ): PredictResponse
}