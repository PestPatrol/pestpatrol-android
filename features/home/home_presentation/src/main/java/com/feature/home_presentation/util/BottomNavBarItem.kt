package com.feature.home_presentation.util

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.core.common.navigation_constants.HomeFeature
import com.feature.home_presentation.R

sealed class BottomNavBarItem(
    val route: HomeFeature,
    @StringRes val titleRes: Int,
    @DrawableRes val iconRes: Int
) {

    data object Home : BottomNavBarItem(
        route = HomeFeature.HomeScreen,
        titleRes = R.string.home,
        iconRes = R.drawable.ic_home
    )

    data object SnapHistory : BottomNavBarItem(
        route = HomeFeature.SnapHistoryScreen,
        titleRes = R.string.snap_history,
        iconRes = R.drawable.ic_snap_history
    )
}
