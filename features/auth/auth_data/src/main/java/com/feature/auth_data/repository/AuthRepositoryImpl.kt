package com.feature.auth_data.repository

import com.core.network.data.GlobalErrorParser
import com.core.network.data_store.UserDataStore
import com.feature.auth_data.mapper.toLoginRequestDto
import com.feature.auth_data.mapper.toLoginResponse
import com.feature.auth_data.mapper.toRegisterRequestDto
import com.feature.auth_data.mapper.toRegisterResponse
import com.feature.auth_data.remote.network.AuthService
import com.feature.auth_domain.model.LoginRequest
import com.feature.auth_domain.model.LoginResponse
import com.feature.auth_domain.model.RegisterRequest
import com.feature.auth_domain.model.RegisterResponse
import com.feature.auth_domain.repository.AuthRepository
import kotlinx.coroutines.flow.first

class AuthRepositoryImpl(
    private val authService: AuthService,
    private val globalErrorParser: GlobalErrorParser,
    private val userDataStore: UserDataStore
): AuthRepository {
    override suspend fun register(request: RegisterRequest): RegisterResponse {
        val response = authService.register(request.toRegisterRequestDto())
        if (response.isSuccessful) {
            response.body()?.let { data -> return data.toRegisterResponse() }
        }
        val error = globalErrorParser.parse(response.errorBody()?.string())
        throw Exception(error)
    }

    override suspend fun login(request: LoginRequest): LoginResponse {
        val response = authService.login(request.toLoginRequestDto())
        if (response.isSuccessful) {
            response.body()?.let { data ->
                userDataStore.setToken(data.data?.token)
                return data.toLoginResponse()
            }
        }
        val error = globalErrorParser.parse(response.errorBody()?.string())
        throw Exception(error)
    }

    override suspend fun getToken(): String {
        return userDataStore.getToken().first()
    }
}