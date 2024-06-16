package com.feature.auth_domain.repository

import com.feature.auth_domain.model.LoginRequest
import com.feature.auth_domain.model.LoginResponse
import com.feature.auth_domain.model.RegisterRequest
import com.feature.auth_domain.model.RegisterResponse

interface AuthRepository {

    suspend fun register(
        request: RegisterRequest
    ): RegisterResponse

    suspend fun login(
        request: LoginRequest
    ): LoginResponse

    suspend fun checkToken(): Boolean
}