package com.feature.auth_data.remote.network

import com.feature.auth_data.remote.dto.LoginRequestDto
import com.feature.auth_data.remote.dto.LoginResponseDto
import com.feature.auth_data.remote.dto.RegisterRequestDto
import com.feature.auth_data.remote.dto.RegisterResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("register")
    suspend fun register(
        @Body request: RegisterRequestDto
    ): Response<RegisterResponseDto>

    @POST("login")
    suspend fun login(
        @Body request: LoginRequestDto
    ): Response<LoginResponseDto>
}