package com.bangkit.pestpatrol.di

import com.bangkit.pestpatrol.navigation.NavigationProvider
import com.feature.auth_presentation.navigation.AuthApi
import com.feature.home_presentation.navigation.HomeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideNavigationProvider(
        authApi: AuthApi,
        homeApi: HomeApi
    ): NavigationProvider {
        return NavigationProvider(
            authApi = authApi,
            homeApi = homeApi
        )
    }
}