package com.feature.home_domain.di

import com.feature.home_domain.repository.HomeRepository
import com.feature.home_domain.use_case.GetAllArticlesUseCase
import com.feature.home_domain.use_case.GetPredictionHistoryUseCase
import com.feature.home_domain.use_case.GetProfileUseCase
import com.feature.home_domain.use_case.HomeUseCases
import com.feature.home_domain.use_case.LogoutUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HomeDomainModule {

    @Provides
    fun provideHomeUseCases(
        homeRepository: HomeRepository
    ) = HomeUseCases(
        getAllArticlesUseCase = GetAllArticlesUseCase(homeRepository),
        getProfileUseCase = GetProfileUseCase(homeRepository),
        getPredictionHistoryUseCase = GetPredictionHistoryUseCase(homeRepository),
        logoutUseCase = LogoutUseCase(homeRepository)
    )
}