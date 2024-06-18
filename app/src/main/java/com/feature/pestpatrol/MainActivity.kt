package com.feature.pestpatrol

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.feature.pestpatrol.navigation.AppNavGraph
import com.feature.pestpatrol.navigation.NavigationProvider
import com.feature.pestpatrol.ui.component.PestPatrolBottomNavBar
import com.feature.pestpatrol.ui.theme.PestPatrolTheme
import com.core.common.ui.Primary50
import com.core.common.ui.Primary600
import com.core.common.util.getRouteName
import com.feature.home_presentation.util.BottomNavBarItem
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationProvider: NavigationProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Primary600.toArgb())
        )
        setContent {
            PestPatrolTheme {
                val navController = rememberNavController()
                App(
                    navHostController = navController,
                    navigationProvider = navigationProvider
                )
            }
        }
    }
}

@Composable
fun App(
    navHostController: NavHostController,
    navigationProvider: NavigationProvider
) {
    val screens = listOf(
        BottomNavBarItem.Home,
        BottomNavBarItem.SnapHistory
    )
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val isBottomBarVisible = screens.any {
        it.route.toString() == currentDestination?.route.getRouteName()
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Scaffold(
            bottomBar = {
                PestPatrolBottomNavBar(
                    navController = navHostController,
                    showBottomNavBar = isBottomBarVisible,
                    screens = screens,
                    currentDestination = currentDestination
                )
            },
            floatingActionButton = {
                AnimatedVisibility(visible = isBottomBarVisible) {
                    Icon(
                        painter = painterResource(id = com.feature.home_presentation.R.drawable.ic_snap_detection),
                        contentDescription = stringResource(R.string.snap_detection),
                        tint = Color.Black,
                        modifier = Modifier
                            .offset(y = 48.dp)
                            .background(Primary50)
                            .clip(CircleShape)
                    )
                }
            },
            floatingActionButtonPosition = FabPosition.Center
        ) {
            AppNavGraph(
                navController = navHostController,
                navigationProvider = navigationProvider,
                paddingValues = it,
                showBottomNavBar = isBottomBarVisible
            )
        }
    }
}