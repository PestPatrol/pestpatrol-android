package com.feature.snap_detection_presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

class SnapDetectionApiImpl: SnapDetectionApi {
    override fun registerGraph(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        InternalSnapDetectionFeatureApi.registerGraph(
            navController = navController,
            navGraphBuilder = navGraphBuilder
        )
    }
}