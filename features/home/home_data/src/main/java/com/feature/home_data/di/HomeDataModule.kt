package com.feature.home_data.di

import com.core.network.data.GlobalErrorParser
import com.core.network.data_store.UserDataStore
import com.feature.home_data.remote.network.HomeService
import com.feature.home_data.repository.HomeRepositoryImpl
import com.feature.home_domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeDataModule {

    @Provides
    @Singleton
    fun provideHomeService(
        retrofit: Retrofit
    ): HomeService = retrofit.create(HomeService::class.java)

    @Provides
    @Singleton
    fun provideHomeRepository(
        homeService: HomeService,
        globalErrorParser: GlobalErrorParser,
        userDataStore: UserDataStore
    ): HomeRepository = HomeRepositoryImpl(
        homeService = homeService,
        globalErrorParser = globalErrorParser,
        userDataStore = userDataStore
    )
}