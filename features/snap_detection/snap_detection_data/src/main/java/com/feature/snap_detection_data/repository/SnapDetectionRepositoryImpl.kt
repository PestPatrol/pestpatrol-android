package com.feature.snap_detection_data.repository

import android.app.Application
import android.graphics.Bitmap
import android.net.Uri
import com.core.common.util.FileUtil
import com.core.common.util.formatBearerToken
import com.core.network.data.GlobalErrorParser
import com.core.network.data_store.UserDataStore
import com.feature.snap_detection.model.PredictResponse
import com.feature.snap_detection.repository.SnapDetectionRepository
import com.feature.snap_detection_data.mapper.toPredictResponse
import com.feature.snap_detection_data.remote.network.SnapDetectionService
import kotlinx.coroutines.flow.first
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody

class SnapDetectionRepositoryImpl(
    private val snapDetectionService: SnapDetectionService,
    private val userDataStore: UserDataStore,
    private val globalErrorParser: GlobalErrorParser,
    private val application: Application
): SnapDetectionRepository {
    override suspend fun predict(uri: Uri): PredictResponse {
        val file = FileUtil.uriToFile(uri, application)
        val reducedFile = FileUtil.reduceFileImage(file)
        val requestImageFile = reducedFile.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val imageMultipart = MultipartBody.Part.createFormData(
            "image-predict",
            reducedFile.name,
            requestImageFile
        )

        val token = userDataStore.getToken().first()
        val response = snapDetectionService.predict(
            token = token.formatBearerToken(),
            file = imageMultipart
        )

        if (response.isSuccessful) {
            response.body()?.let { data -> return data.data?.toPredictResponse() ?: throw NullPointerException() }
        }
        val error = globalErrorParser.parse(response.errorBody()?.string())
        throw Exception(error)
    }

    override suspend fun predict(bitmap: Bitmap): PredictResponse {
        val file = FileUtil.bitmapToFile(bitmap, application)
        val reducedFile = FileUtil.reduceFileImage(file)
        val requestImageFile = reducedFile.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val imageMultipart = MultipartBody.Part.createFormData(
            "image-predict",
            reducedFile.name,
            requestImageFile
        )

        val token = userDataStore.getToken().first()
        val response = snapDetectionService.predict(
            token = token.formatBearerToken(),
            file = imageMultipart
        )

        if (response.isSuccessful) {
            response.body()?.let { data -> return data.data?.toPredictResponse() ?: throw NullPointerException() }
        }
        val error = globalErrorParser.parse(response.errorBody()?.string())
        throw Exception(error)
    }
}