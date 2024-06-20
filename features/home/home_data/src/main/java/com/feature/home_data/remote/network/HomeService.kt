package com.feature.home_data.remote.network

import com.core.network.data.dto.BaseResponse
import com.feature.home_data.remote.dto.GetAllArticlesResponseDto
import com.feature.home_data.remote.dto.PredictionHistoryResponseDto
import com.feature.home_data.remote.dto.ProfileResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface HomeService {

    @GET("articles")
    suspend fun getAllArticles(
        @Header("Authorization") token: String
    ): Response<GetAllArticlesResponseDto>

    @GET("profile")
    suspend fun getProfile(
        @Header("Authorization") token: String
    ): Response<BaseResponse<ProfileResponseDto>>

    @GET("/predict/history")
    suspend fun getPredictionHistory(
        @Header("Authorization") token: String
    ): Response<BaseResponse<List<PredictionHistoryResponseDto>>>
}