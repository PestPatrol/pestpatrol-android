package com.feature.auth_domain.use_case

data class AuthUseCases(
    val loginUseCase: LoginUseCase,
    val registerUseCase: RegisterUseCase,
    val checkTokenUseCase: CheckTokenUseCase
)