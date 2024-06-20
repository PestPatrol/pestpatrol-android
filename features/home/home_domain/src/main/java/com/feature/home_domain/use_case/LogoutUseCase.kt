package com.feature.home_domain.use_case

import com.core.common.util.SimpleResource
import com.feature.home_domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LogoutUseCase(
    private val repository: HomeRepository
) {

    operator fun invoke(): Flow<SimpleResource> = flow {
        repository.logout()
    }
}