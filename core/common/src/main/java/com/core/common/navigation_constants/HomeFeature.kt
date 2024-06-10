package com.core.common.navigation_constants

import kotlinx.serialization.Serializable

@Serializable
sealed class HomeFeature {

    @Serializable
    data object NestedRoute: HomeFeature()

    @Serializable
    data object HomeScreen: HomeFeature()

    @Serializable
    data object SnapHistory: HomeFeature()
}