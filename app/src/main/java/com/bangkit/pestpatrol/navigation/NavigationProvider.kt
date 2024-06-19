package com.bangkit.pestpatrol.navigation

import com.feature.auth_presentation.navigation.AuthApi
import com.feature.home_presentation.navigation.HomeApi
import com.feature.snap_detection_presentation.navigation.SnapDetectionApi

data class NavigationProvider(
    val authApi: AuthApi,
    val homeApi: HomeApi,
    val snapDetectionApi: SnapDetectionApi
)