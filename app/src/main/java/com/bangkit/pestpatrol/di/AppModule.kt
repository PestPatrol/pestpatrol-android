package com.bangkit.pestpatrol.di

import com.bangkit.pestpatrol.navigation.NavigationProvider
import com.feature.auth_presentation.navigation.AuthApi
import com.feature.home_presentation.navigation.HomeApi
import com.feature.snap_detection_presentation.navigation.SnapDetectionApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNavigationProvider(
        authApi: AuthApi,
        homeApi: HomeApi,
        snapDetectionApi: SnapDetectionApi
    ): NavigationProvider {
        return NavigationProvider(
            authApi = authApi,
            homeApi = homeApi,
            snapDetectionApi = snapDetectionApi
        )
    }
}