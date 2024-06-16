package com.bangkit.pestpatrol.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
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
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.core.common.ui.Primary25
import com.core.common.ui.Primary400
import com.core.common.ui.Primary50
import com.core.common.ui.SFProDisplayMedium
import com.core.common.util.getRouteName
import com.feature.home_presentation.util.BottomNavBarItem

@Composable
fun PestPatrolBottomNavBar(
    navController: NavController,
    showBottomNavBar: Boolean,
    screens: List<BottomNavBarItem>,
    currentDestination: NavDestination?
) {
    AnimatedVisibility(showBottomNavBar) {
        BottomNavigation(
            backgroundColor = Primary400,
            modifier = Modifier
                .background(Primary25)
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .height(64.dp)
        ) {
            screens.forEach { screen ->
                BottomNavigationItem(
                    selected = currentDestination?.hierarchy?.any {
                        it.route.getRouteName() == screen.route.toString()
                    } == true,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id)
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = screen.iconRes),
                            contentDescription = stringResource(
                                id = screen.titleRes
                            ),
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(id = screen.titleRes),
                            fontFamily = SFProDisplayMedium,
                            fontSize = 16.sp
                        )
                    },
                    unselectedContentColor = Color.White,
                    selectedContentColor = Primary50
                )
            }
        }
    }
}