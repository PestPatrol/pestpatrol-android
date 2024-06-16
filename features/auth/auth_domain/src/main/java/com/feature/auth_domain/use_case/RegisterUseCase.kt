package com.feature.auth_domain.use_case

import com.core.common.util.Resource
import com.core.common.util.UiText
import com.feature.auth_domain.model.RegisterRequest
import com.feature.auth_domain.model.RegisterResponse
import com.feature.auth_domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RegisterUseCase(
    private val repository: AuthRepository
) {

    operator fun invoke(
        fullName: String,
        email: String,
        password: String
    ): Flow<Resource<RegisterResponse>> = flow {
        emit(Resource.Loading())
        try {
            val request = RegisterRequest(
                fullName = fullName,
                email = email,
                password = password
            )
            val response = repository.register(request)
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(UiText.DynamicString(e.message.toString())))
        }
    }
        .flowOn(Dispatchers.IO)
}