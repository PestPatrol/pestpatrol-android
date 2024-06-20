package com.feature.home_presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.core.common.navigation_constants.HomeFeature
import com.feature.feature_api.FeatureApi
import com.feature.home_presentation.screen.home.HomeScreen
import com.feature.home_presentation.screen.profile.ProfileScreen
import com.feature.home_presentation.screen.snap_history.SnapHistoryScreen

object InternalHomeFeatureApi : FeatureApi {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation<HomeFeature.NestedRoute>(
            startDestination = HomeFeature.HomeScreen
        ) {
            composable<HomeFeature.HomeScreen> {
                HomeScreen(navController = navController)
            }
            composable<HomeFeature.SnapHistoryScreen> {
                SnapHistoryScreen(navController = navController)
            }
            composable<HomeFeature.ProfileScreen> {
                ProfileScreen(navController = navController)
            }
        }
    }
}