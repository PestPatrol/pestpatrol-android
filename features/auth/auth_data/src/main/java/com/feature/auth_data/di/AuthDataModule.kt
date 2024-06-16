package com.feature.auth_data.di

import com.core.network.data.GlobalErrorParser
import com.core.network.data_store.UserDataStore
import com.feature.auth_data.remote.network.AuthService
import com.feature.auth_data.repository.AuthRepositoryImpl
import com.feature.auth_domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object AuthDataModule {

    @Provides
    fun provideAuthService(
        retrofit: Retrofit
    ): AuthService = retrofit.create(AuthService::class.java)

    @Provides
    fun provideAuthRepository(
        authService: AuthService,
        globalErrorParser: GlobalErrorParser,
        userDataStore: UserDataStore
    ): AuthRepository = AuthRepositoryImpl(
        authService = authService,
        globalErrorParser = globalErrorParser,
        userDataStore = userDataStore
    )
}