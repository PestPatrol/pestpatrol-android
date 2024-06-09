package com.bangkit.pestpatrol.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.core.common.navigation_constants.AuthFeature

@Composable
fun AppNavGraph(
    navController: NavHostController,
    navigationProvider: NavigationProvider
) {
    NavHost(
        navController = navController,
        startDestination = AuthFeature.NestedRoute
    ) {
        navigationProvider.authApi.registerGraph(
            navController = navController,
            navGraphBuilder = this
        )


    }
}