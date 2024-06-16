package com.feature.auth_presentation.screen.splash

import android.content.pm.ActivityInfo
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.core.common.component.LockScreenOrientation
import com.core.common.navigation_constants.AuthFeature
import com.core.common.navigation_constants.HomeFeature
import com.core.common.resource.AppLogo
import com.core.common.resource.AppLogoDesc
import com.core.common.resource.AppName
import com.core.common.ui.BalooBhaijaanRegular
import com.core.common.ui.Primary100
import com.core.common.ui.Primary200
import com.core.common.ui.Primary500
import com.core.common.ui.SFProDisplayMedium
import com.core.common.ui.components.LoadingDialog
import com.core.common.util.ObserveAsEvents
import com.core.common.util.UiText
import com.feature.auth_presentation.R

@Composable
fun SplashScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<SplashViewModel>()
    val context = LocalContext.current

    val isLoading by viewModel.isLoading
    var buttonState by remember {
        mutableStateOf(false)
    }

    val alpha = remember {
        Animatable(0f)
    }
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

    ObserveAsEvents(flow = viewModel.errorMessage) { uiText ->
        when (uiText) {
            is UiText.DynamicString -> {
                Toast.makeText(
                    context,
                    uiText.value,
                    Toast.LENGTH_SHORT
                ).show()
            }

            is UiText.StringResource -> {
                Toast.makeText(
                    context,
                    context.getString(uiText.id),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    ObserveAsEvents(flow = viewModel.isTokenAuthorized) {
        if (it) {
            navController.navigate(HomeFeature.HomeScreen)
            return@ObserveAsEvents
        }

        buttonState = true
    }

    LaunchedEffect(Unit) {
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 2000
            )
        )
    }

    if (isLoading)
        LoadingDialog()

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
                painter = painterResource(AppLogo),
                contentDescription = stringResource(AppLogoDesc),
                modifier = Modifier
                    .aspectRatio(1f)
                    .width(screenWidth / 3),
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            )
            Text(
                text = stringResource(AppName),
                fontFamily = BalooBhaijaanRegular,
                color = Color.White,
                fontSize = 50.sp,
                modifier = Modifier.offset(y = (-50).dp)
            )
        }

        AnimatedVisibility(
            visible = buttonState,
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                modifier = Modifier
                    .background(
                        Brush.linearGradient(
                            colors = listOf(
                                Primary500,
                                Primary200
                            )
                        ),
                        shape = ButtonDefaults.shape
                    ),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 8.dp),
                onClick = {
                    navController.navigate(AuthFeature.LoginScreen)
                }
            ) {
                Text(
                    text = stringResource(R.string.get_started),
                    color = Color.White,
                    fontFamily = SFProDisplayMedium,
                    fontSize = 20.sp
                )
            }
        }
    }
}