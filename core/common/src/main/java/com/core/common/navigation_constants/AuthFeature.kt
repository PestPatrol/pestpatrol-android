package com.core.common.navigation_constants

import kotlinx.serialization.Serializable

@Serializable
sealed class AuthFeature {

    @Serializable
    data object NestedRoute : AuthFeature()

    @Serializable
    data object SplashScreen : AuthFeature()

    @Serializable
    data object LoginScreen : AuthFeature()

    @Serializable
    data object RegisterScreen : AuthFeature()
}