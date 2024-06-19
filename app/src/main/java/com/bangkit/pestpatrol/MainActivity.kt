package com.bangkit.pestpatrol

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bangkit.pestpatrol.navigation.AppNavGraph
import com.bangkit.pestpatrol.navigation.NavigationProvider
import com.bangkit.pestpatrol.ui.component.PestPatrolBottomNavBar
import com.bangkit.pestpatrol.ui.theme.PestPatrolTheme
import com.core.common.navigation_constants.SnapDetectionFeature
import com.core.common.ui.Primary50
import com.core.common.ui.Primary600
import com.core.common.ui.SFProDisplayMedium
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
        BottomNavBarItem.SnapDetection,
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
                if(isBottomBarVisible) {
                    Column(
                        modifier = Modifier
                            .offset(y = 72.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        IconButton(
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(60.dp)
                                .background(Primary50),
                            onClick = {
                                navHostController.navigate(SnapDetectionFeature.NestedRoute)
                            }
                        ) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Icon(
                                    painter = painterResource(id = com.feature.home_presentation.R.drawable.ic_snap_detection),
                                    contentDescription = stringResource(R.string.snap_detection),
                                    tint = Primary600,
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        }
                        Text(
                            text = stringResource(id = R.string.snap_detection),
                            fontFamily = SFProDisplayMedium,
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }
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