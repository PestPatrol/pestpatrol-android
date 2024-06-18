package com.feature.snap_detection_presentation.di

import com.feature.snap_detection_presentation.navigation.SnapDetectionApi
import com.feature.snap_detection_presentation.navigation.SnapDetectionApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SnapDetectionPresentationModule {

    @Provides
    fun provideSnapDetectionApi(): SnapDetectionApi = SnapDetectionApiImpl()
}