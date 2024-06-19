package com.feature.snap_detection_data.remote.network

import com.core.network.data.dto.BaseResponse
import com.feature.snap_detection_data.remote.dto.PredictResponseDto
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface SnapDetectionService {

    @Multipart
    @POST("predict")
    suspend fun predict(
        @Header("Authorization") token: String,
        @Part file: MultipartBody.Part
    ): Response<BaseResponse<PredictResponseDto>>
}