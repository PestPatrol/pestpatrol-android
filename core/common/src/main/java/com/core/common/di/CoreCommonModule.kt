package com.core.common.di

import android.content.Context
import com.core.common.util.AppExecutors
import com.core.common.util.NetworkConnectivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreCommonModule {

    @Provides
    @Singleton
    fun provideAppExecutors() = AppExecutors()

    @Provides
    @Singleton
    fun provideNetworkConnectivity(
        appExecutors: AppExecutors,
        @ApplicationContext context: Context
    ): NetworkConnectivity = NetworkConnectivity(
        appExecutors = appExecutors,
        context = context
    )
}