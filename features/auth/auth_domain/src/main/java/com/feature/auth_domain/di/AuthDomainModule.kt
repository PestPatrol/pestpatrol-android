package com.feature.auth_domain.di

import com.feature.auth_domain.repository.AuthRepository
import com.feature.auth_domain.use_case.AuthUseCases
import com.feature.auth_domain.use_case.CheckTokenUseCase
import com.feature.auth_domain.use_case.LoginUseCase
import com.feature.auth_domain.use_case.RegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AuthDomainModule {

    @Provides
    fun provideAuthUseCases(
        repository: AuthRepository
    ): AuthUseCases = AuthUseCases(
        loginUseCase = LoginUseCase(repository),
        registerUseCase = RegisterUseCase(repository),
        checkTokenUseCase = CheckTokenUseCase(repository)
    )
}