package com.feature.snap_detection.di

import com.feature.snap_detection.repository.SnapDetectionRepository
import com.feature.snap_detection.use_case.PredictUseCase
import com.feature.snap_detection.use_case.SnapDetectionUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SnapDetectionDomainModule {

    @Provides
    fun provideSnapDetectionUseCases(
        repository: SnapDetectionRepository
    ) = SnapDetectionUseCases(
        predictUseCase = PredictUseCase(repository)
    )
}