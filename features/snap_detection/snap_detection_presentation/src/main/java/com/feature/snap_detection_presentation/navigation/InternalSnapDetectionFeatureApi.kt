package com.feature.snap_detection_presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.core.common.navigation_constants.SnapDetectionFeature
import com.feature.feature_api.FeatureApi
import com.feature.snap_detection_presentation.screen.choose_image.ChooseImageScreen

object InternalSnapDetectionFeatureApi: FeatureApi {
    override fun registerGraph(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.navigation<SnapDetectionFeature.NestedRoute>(
            startDestination = SnapDetectionFeature.ChooseImageScreen
        ) {
            composable<SnapDetectionFeature.ChooseImageScreen> {
                ChooseImageScreen(navController)
            }

            composable<SnapDetectionFeature.CameraScreen> {

            }
        }
    }
}