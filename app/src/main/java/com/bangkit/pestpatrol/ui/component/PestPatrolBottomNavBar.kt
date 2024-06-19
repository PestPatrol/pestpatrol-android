package com.bangkit.pestpatrol.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.core.common.navigation_constants.SnapDetectionFeature
import com.core.common.ui.Primary400
import com.core.common.ui.Primary50
import com.core.common.ui.SFProDisplayMedium
import com.core.common.ui.interaction.singleClick
import com.core.common.util.getRouteName
import com.feature.home_presentation.util.BottomNavBarItem

@Composable
fun PestPatrolBottomNavBar(
    navController: NavController,
    showBottomNavBar: Boolean,
    screens: List<BottomNavBarItem<Any>>,
    currentDestination: NavDestination?
) {
    if(showBottomNavBar) {
        BottomNavigation(
            backgroundColor = Primary400,
            modifier = Modifier
                .background(Color.Transparent)
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .height(80.dp)
        ) {
            screens.forEach { screen ->
                BottomNavigationItem(
                    selected =
                    currentDestination?.hierarchy?.any {
                        it.route.getRouteName() == screen.route.toString()
                    } == true,
                    onClick = singleClick {
                        if (screen.route.toString() == SnapDetectionFeature.NestedRoute.toString()) {
                            navController.navigate(SnapDetectionFeature.NestedRoute)
                            return@singleClick
                        }

                        if (screen.route.toString() != currentDestination?.route.getRouteName())
                            navController.navigate(screen.route ?: return@singleClick) {
                                popUpTo(navController.graph.id) {
                                    saveState = true
                                    inclusive = true
                                }
                                restoreState = true
                            }
                    },
                    icon = {
                        if (screen.iconRes != null) {
                            Icon(
                                painter = painterResource(id = screen.iconRes!!),
                                contentDescription = stringResource(
                                    id = screen.titleRes!!
                                ),
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                    },
                    label = {
                        if (screen.titleRes != null)
                            Text(
                                text = stringResource(id = screen.titleRes!!),
                                fontFamily = SFProDisplayMedium,
                                fontSize = 14.sp
                            )
                    },
                    unselectedContentColor = Color.White,
                    selectedContentColor = Primary50
                )
            }
        }
    }
}