package com.feature.auth_presentation.di

import com.feature.auth_presentation.navigation.AuthApi
import com.feature.auth_presentation.navigation.AuthApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AuthPresentationModule {

    @Provides
    fun provideAuthApi(): AuthApi {
        return AuthApiImpl()
    }
}