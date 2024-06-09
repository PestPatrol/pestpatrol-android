package com.feature.auth_presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

class AuthApiImpl: AuthApi {

    override fun registerGraph(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        InternalAuthFeatureApi.registerGraph(
            navController,
            navGraphBuilder
        )
    }
}