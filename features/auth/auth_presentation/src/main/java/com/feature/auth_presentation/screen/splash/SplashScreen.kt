package com.feature.auth_presentation.screen.splash

import android.content.pm.ActivityInfo
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.core.common.R
import com.core.common.component.LockScreenOrientation
import com.core.common.navigation_constants.AuthFeature
import com.core.common.ui.Primary100
import com.core.common.ui.Primary200
import com.core.common.ui.Primary500

@Composable
fun SplashScreen(
    navController: NavController
) {
    val alpha = remember {
        Animatable(0f)
    }
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

    LaunchedEffect(Unit) {
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 2000
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary100)
            .padding(32.dp)
            .padding(bottom = 24.dp)
            .alpha(alpha.value)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.logo_app),
                contentDescription = stringResource(R.string.app_logo),
                modifier = Modifier
                    .aspectRatio(1f)
                    .width(screenWidth / 3),
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            )
            Text(
                text = stringResource(R.string.app_name),
                fontFamily = FontFamily(Font(R.font.baloo_bhaijaan_regular)),
                color = Color.White,
                fontSize = 50.sp,
                modifier = Modifier.offset(y = (-50).dp)
            )
        }

        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Primary500,
                            Primary200
                        )
                    ),
                    shape = ButtonDefaults.shape
                ),
            onClick = {
                navController.navigate(AuthFeature.LoginScreen)
            }
        ) {
            Text(
                text = stringResource(com.feature.auth_presentation.R.string.get_started),
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.sfprodisplay_medium)),
                fontSize = 20.sp
            )
        }
    }
}