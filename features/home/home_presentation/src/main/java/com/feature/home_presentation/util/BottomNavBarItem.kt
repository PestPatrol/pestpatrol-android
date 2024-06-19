package com.feature.home_presentation.util

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.core.common.navigation_constants.HomeFeature
import com.core.common.navigation_constants.SnapDetectionFeature
import com.feature.home_presentation.R

sealed class BottomNavBarItem<out T>(
    val route: T?,
    @StringRes val titleRes: Int?,
    @DrawableRes val iconRes: Int?
) {

    data object Home : BottomNavBarItem<HomeFeature>(
        route = HomeFeature.HomeScreen,
        titleRes = R.string.home,
        iconRes = R.drawable.ic_home
    )

    data object SnapDetection : BottomNavBarItem<SnapDetectionFeature>(
        route = SnapDetectionFeature.NestedRoute,
        titleRes = null,
        iconRes = null
    )

    data object SnapHistory : BottomNavBarItem<HomeFeature>(
        route = HomeFeature.SnapHistoryScreen,
        titleRes = R.string.snap_history,
        iconRes = R.drawable.ic_snap_history
    )
}
