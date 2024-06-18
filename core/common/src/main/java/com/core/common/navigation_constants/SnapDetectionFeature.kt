package com.core.common.navigation_constants

import kotlinx.serialization.Serializable

@Serializable
sealed class SnapDetectionFeature {

    @Serializable
    data object NestedRoute : SnapDetectionFeature()

    @Serializable
    data object ChooseImageScreen : SnapDetectionFeature()

    @Serializable
    data object CameraScreen : SnapDetectionFeature()
}
