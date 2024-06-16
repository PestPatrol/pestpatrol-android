package com.feature.auth_domain.use_case

import com.core.common.util.Resource
import com.core.common.util.UiText
import com.feature.auth_domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CheckTokenUseCase(
    private val repository: AuthRepository
) {

    operator fun invoke(): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            val response = repository.checkToken()
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(UiText.DynamicString(e.message.toString())))
        }
    }
        .flowOn(Dispatchers.IO)
}