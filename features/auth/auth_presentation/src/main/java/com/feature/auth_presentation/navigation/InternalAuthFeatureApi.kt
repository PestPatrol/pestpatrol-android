package com.feature.auth_presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.feature.feature_api.FeatureApi
import com.core.common.navigation_constants.AuthFeature
import com.feature.auth_presentation.screen.login.LoginScreen
import com.feature.auth_presentation.screen.register.RegisterScreen
import com.feature.auth_presentation.screen.splash.SplashScreen

internal object InternalAuthFeatureApi : FeatureApi {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation<AuthFeature.NestedRoute>(
            startDestination = AuthFeature.SplashScreen,
        ) {
            composable<AuthFeature.SplashScreen> {
                SplashScreen(navController)
            }

            composable<AuthFeature.LoginScreen> {
                LoginScreen(navController)
            }

            composable<AuthFeature.RegisterScreen> {
                RegisterScreen(navController)
            }
        }
    }
}