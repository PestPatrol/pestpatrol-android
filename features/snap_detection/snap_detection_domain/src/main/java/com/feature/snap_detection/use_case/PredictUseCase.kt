package com.feature.snap_detection.use_case

import android.graphics.Bitmap
import android.net.Uri
import com.core.common.util.Resource
import com.core.common.util.UiText
import com.feature.snap_detection.model.PredictResponse
import com.feature.snap_detection.repository.SnapDetectionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PredictUseCase(
    private val repository: SnapDetectionRepository
) {

    operator fun invoke(uri: Uri): Flow<Resource<PredictResponse>> = flow {
        emit(Resource.Loading())
        try {
            val response = repository.predict(uri)
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(UiText.DynamicString(e.message.toString())))
        }
    }
        .flowOn(Dispatchers.IO)

    operator fun invoke(bitmap: Bitmap): Flow<Resource<PredictResponse>> = flow {
        emit(Resource.Loading())
        try {
            val response = repository.predict(bitmap)
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(UiText.DynamicString(e.message.toString())))
        }
    }
        .flowOn(Dispatchers.IO)
}