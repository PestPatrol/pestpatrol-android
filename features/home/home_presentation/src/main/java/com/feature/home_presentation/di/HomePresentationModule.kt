package com.feature.home_presentation.di

import com.feature.home_presentation.navigation.HomeApi
import com.feature.home_presentation.navigation.HomeApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HomePresentationModule {

    @Provides
    fun provideHomeApi(): HomeApi {
        return HomeApiImpl()
    }
}