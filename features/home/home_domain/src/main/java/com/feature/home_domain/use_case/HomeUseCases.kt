package com.feature.home_domain.use_case

data class HomeUseCases(
    val getAllArticlesUseCase: GetAllArticlesUseCase,
    val getProfileUseCase: GetProfileUseCase,
    val getPredictionHistoryUseCase: GetPredictionHistoryUseCase,
    val logoutUseCase: LogoutUseCase,
    val getArticleByIdUseCase: GetArticleByIdUseCase
)
