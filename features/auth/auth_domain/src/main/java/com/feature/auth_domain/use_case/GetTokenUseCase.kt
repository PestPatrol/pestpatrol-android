package com.feature.auth_domain.use_case

import com.feature.auth_domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetTokenUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(): String = repository.getToken()
}