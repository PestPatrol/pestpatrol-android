package com.feature.home_data.remote.network

import com.feature.home_data.remote.dto.GetAllArticlesResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface HomeService {

    @GET
    suspend fun getAllArticles(
        @Header("Authorization") token: String
    ): Response<GetAllArticlesResponseDto>


}