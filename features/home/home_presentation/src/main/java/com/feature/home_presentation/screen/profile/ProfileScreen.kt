package com.feature.home_presentation.screen.profile

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.core.common.navigation_constants.AuthFeature
import com.core.common.navigation_constants.HomeFeature
import com.core.common.resource.IconArrowBack
import com.core.common.resource.IconArrowForward
import com.core.common.resource.IconImport
import com.core.common.resource.IconProfileRounded
import com.core.common.resource.ProfileIconDescription
import com.core.common.ui.HintTextColor
import com.core.common.ui.Primary25
import com.core.common.ui.Primary600
import com.core.common.ui.PrimaryScreenBackgroundGradient
import com.core.common.ui.PrimaryTextColor
import com.core.common.ui.RedPrediction
import com.core.common.ui.SFProDisplayBold
import com.core.common.ui.SFProDisplayMedium
import com.core.common.ui.SFProDisplayRegular
import com.core.common.ui.components.LoadingDialog
import com.core.common.ui.interaction.disableSplitMotionEvents
import com.core.common.ui.interaction.singleClick
import com.core.common.util.ObserveAsEvents
import com.core.common.util.UiText
import com.feature.home_presentation.R

@Composable
fun ProfileScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<ProfileViewModel>()
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val profile by viewModel.profileData.collectAsStateWithLifecycle(initialValue = null)
    val isLoading by viewModel.profileLoading.collectAsStateWithLifecycle(initialValue = true)

    ObserveAsEvents(flow = viewModel.profileError) { uiText ->
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

    LaunchedEffect(Unit) {
        viewModel.getProfile()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryScreenBackgroundGradient)
            .scrollable(scrollState, Orientation.Vertical)
    ) {
        if (isLoading)
            LoadingDialog()
        else {
            Spacer(modifier = Modifier.height(24.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                IconButton(
                    modifier = Modifier
                        .size(36.dp)
                        .align(Alignment.CenterStart)
                        .disableSplitMotionEvents(),
                    onClick = singleClick {
                        navController.navigateUp()
                    }
                ) {
                    Icon(
                        painter = painterResource(id = IconArrowBack),
                        contentDescription = stringResource(id = R.string.back),
                        tint = Color.White,
                        modifier = Modifier.size(36.dp)
                    )
                }
                Text(
                    text = stringResource(R.string.my_profile), fontFamily = SFProDisplayBold,
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Spacer(modifier = Modifier.height(64.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Primary25)
            ) {
                Card(
                    modifier = Modifier
                        .offset(y = (-48).dp)
                        .fillMaxWidth(),
                    shape = CircleShape,
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.align(Alignment.CenterStart)
                        ) {
                            SubcomposeAsyncImage(
                                model = profile?.profPicLink?.ifEmpty { IconProfileRounded },
                                contentDescription = stringResource(id = ProfileIconDescription),
                                loading = {
                                    CircularProgressIndicator(color = Primary600)
                                },
                                modifier = Modifier
                                    .size(64.dp)
                                    .clip(CircleShape)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(
                                    text = profile?.fullName.toString(),
                                    fontFamily = SFProDisplayMedium,
                                    fontSize = 16.sp,
                                    color = PrimaryTextColor,
                                )
                                Text(
                                    text = profile?.email.toString(),
                                    fontFamily = SFProDisplayRegular,
                                    fontSize = 12.sp,
                                    color = HintTextColor
                                )
                            }
                        }
                        Icon(
                            painter = painterResource(id = IconImport),
                            contentDescription = stringResource(R.string.edit_profile),
                            tint = Primary600,
                            modifier = Modifier.align(Alignment.CenterEnd)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Card(
                    modifier = Modifier
                        .fillMaxSize(),
                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 24.dp)
                    ) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    viewModel.logout()
                                    navController.navigate(AuthFeature.NestedRoute) {
                                        popUpTo(HomeFeature.HomeScreen) {
                                            inclusive = true
                                        }
                                    }
                                },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_logout),
                                contentDescription = stringResource(R.string.logout),
                                modifier = Modifier
                                    .size(36.dp)
                                    .weight(1f)
                            )
                            Column(
                                modifier = Modifier.weight(5f)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.logout),
                                    fontFamily = SFProDisplayMedium,
                                    color = RedPrediction,
                                    fontSize = 16.sp
                                )
                                Text(
                                    text = stringResource(R.string.logout_from_your_account),
                                    fontFamily = SFProDisplayRegular,
                                    color = PrimaryTextColor,
                                    fontSize = 14.sp
                                )
                            }
                            Icon(
                                painter = painterResource(id = IconArrowForward),
                                contentDescription = stringResource(id = R.string.logout),
                                tint = RedPrediction,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }
        }
    }
}