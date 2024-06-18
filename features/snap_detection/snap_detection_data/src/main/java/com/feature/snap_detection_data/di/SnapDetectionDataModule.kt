package com.feature.snap_detection_data.di

import android.app.Application
import com.core.network.data.GlobalErrorParser
import com.core.network.data_store.UserDataStore
import com.feature.snap_detection.repository.SnapDetectionRepository
import com.feature.snap_detection_data.remote.network.SnapDetectionService
import com.feature.snap_detection_data.repository.SnapDetectionRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object SnapDetectionDataModule {

    @Provides
    fun provideSnapDetectionService(
        retrofit: Retrofit
    ): SnapDetectionService = retrofit.create(SnapDetectionService::class.java)

    @Provides
    fun provideSnapDetectionRepository(
        snapDetectionService: SnapDetectionService,
        userDataStore: UserDataStore,
        globalErrorParser: GlobalErrorParser,
        application: Application
    ): SnapDetectionRepository = SnapDetectionRepositoryImpl(
        snapDetectionService = snapDetectionService,
        userDataStore = userDataStore,
        globalErrorParser = globalErrorParser,
        application = application
    )
}