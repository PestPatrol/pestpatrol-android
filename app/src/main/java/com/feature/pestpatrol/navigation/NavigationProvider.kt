package com.feature.pestpatrol.navigation

import com.feature.auth_presentation.navigation.AuthApi
import com.feature.home_presentation.navigation.HomeApi

data class NavigationProvider(
    val authApi: AuthApi,
    val homeApi: HomeApi
)