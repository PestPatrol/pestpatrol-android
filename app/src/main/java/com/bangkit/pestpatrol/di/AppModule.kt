package com.bangkit.pestpatrol.di

import com.bangkit.pestpatrol.navigation.NavigationProvider
import com.feature.auth_presentation.navigation.AuthApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideNavigationProvider(
        authApi: AuthApi
    ): NavigationProvider {
        return NavigationProvider(
            authApi = authApi
        )
    }
}