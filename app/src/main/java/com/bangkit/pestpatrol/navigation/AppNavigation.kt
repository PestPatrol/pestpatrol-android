package com.bangkit.pestpatrol.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.core.common.navigation_constants.AuthFeature

@Composable
fun AppNavGraph(
    navController: NavHostController,
    navigationProvider: NavigationProvider,
    showBottomNavBar: Boolean,
    paddingValues: PaddingValues
) {

    Box(
//        modifier = Modifier.padding(
//            if (showBottomNavBar)
//                PaddingValues(bottom = paddingValues.calculateBottomPadding())
//            else
//                PaddingValues(0.dp)
//        )
    ) {
        NavHost(
            navController = navController,
            startDestination = AuthFeature.NestedRoute
        ) {
            navigationProvider.authApi.registerGraph(
                navController = navController,
                navGraphBuilder = this
            )
            navigationProvider.homeApi.registerGraph(
                navController = navController,
                navGraphBuilder = this
            )
            navigationProvider.snapDetectionApi.registerGraph(
                navController = navController,
                navGraphBuilder = this
            )
        }
    }
}