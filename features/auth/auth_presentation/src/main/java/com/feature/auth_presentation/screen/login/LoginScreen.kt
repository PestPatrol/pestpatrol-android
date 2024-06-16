package com.feature.auth_presentation.screen.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.core.common.navigation_constants.AuthFeature
import com.core.common.navigation_constants.HomeFeature
import com.core.common.resource.IconGoogleCircle
import com.core.common.ui.HintTextColor
import com.core.common.ui.Primary200
import com.core.common.ui.Primary25
import com.core.common.ui.Primary50
import com.core.common.ui.Primary500
import com.core.common.ui.Primary600
import com.core.common.ui.PrimaryTextColor
import com.core.common.ui.SFProDisplayBold
import com.core.common.ui.SFProDisplayMedium
import com.core.common.ui.SFProDisplayRegular
import com.core.common.ui.components.LoadingDialog
import com.core.common.util.ObserveAsEvents
import com.core.common.util.UiText
import com.feature.auth_presentation.R

@Composable
fun LoginScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<LoginViewModel>()
    val context = LocalContext.current
    val isLoading by viewModel.loginLoading

    ObserveAsEvents(flow = viewModel.loginError) { uiText ->
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

    ObserveAsEvents(flow = viewModel.loginData) {
        navController.navigate(HomeFeature.NestedRoute) {
            popUpTo<AuthFeature.SplashScreen> {
                inclusive = true
            }
        }
    }

    if (isLoading)
        LoadingDialog()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary25)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.welcome),
            fontFamily = SFProDisplayBold,
            color = Primary600,
            fontSize = 48.sp
        )
        Text(
            text = stringResource(R.string.sign_in_to_your_account),
            fontFamily = SFProDisplayMedium,
            color = PrimaryTextColor,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = stringResource(R.string.email),
            fontFamily = SFProDisplayMedium,
            color = PrimaryTextColor,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = viewModel.email.value,
            onValueChange = {
                viewModel.email.value = it
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.inputyouremailhere_gmail_com),
                    fontFamily = SFProDisplayRegular,
                    color = HintTextColor,
                    fontSize = 13.sp
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Primary600,
                focusedBorderColor = Primary600,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            ),
            shape = RoundedCornerShape(16.dp),
            textStyle = TextStyle(
                fontFamily = SFProDisplayRegular,
                color = PrimaryTextColor,
                fontSize = 14.sp
            ),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.password),
            fontFamily = SFProDisplayMedium,
            color = PrimaryTextColor,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = viewModel.password.value,
            onValueChange = {
                viewModel.password.value = it
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.input_your_password_min_8_characters),
                    fontFamily = SFProDisplayRegular,
                    color = HintTextColor,
                    fontSize = 13.sp
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Primary600,
                focusedBorderColor = Primary600,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                cursorColor = Primary600
            ),
            shape = RoundedCornerShape(16.dp),
            textStyle = TextStyle(
                fontFamily = SFProDisplayRegular,
                color = PrimaryTextColor,
                fontSize = 14.sp
            ),
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.linearGradient(
                            colors = if (viewModel.isButtonEnabled.value) listOf(
                                Primary500,
                                Primary200
                            ) else listOf(HintTextColor, HintTextColor)
                        ),
                        shape = ButtonDefaults.shape
                    ),
                onClick = {
                    navController.navigate(HomeFeature.NestedRoute) {
                        popUpTo<AuthFeature.SplashScreen> {
                            inclusive = true
                        }
                    }
                }
            ) {
                Text(
                    text = stringResource(R.string.sign_in),
                    fontFamily = SFProDisplayBold,
                    color = Color.White,
                    fontSize = 18.sp
                )
            }

            Text(
                text = stringResource(R.string.or),
                fontFamily = SFProDisplayRegular,
                color = PrimaryTextColor,
                fontSize = 14.sp
            )

            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Primary50),
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
//                    navController.navigate(HomeFeature.NestedRoute) {
//                        popUpTo<AuthFeature.SplashScreen> {
//                            inclusive = true
//                        }
//                    }
                    viewModel.login()
                }
            ) {
                Image(
                    painter = painterResource(id = IconGoogleCircle),
                    contentDescription = stringResource(
                        R.string.continue_with_google
                    ),
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(id = R.string.continue_with_google),
                    fontFamily = SFProDisplayBold,
                    color = Primary600,
                    fontSize = 18.sp
                )
            }

            Row {
                Text(
                    text = stringResource(R.string.don_t_have_an_account),
                    fontFamily = SFProDisplayRegular,
                    color = PrimaryTextColor,
                    fontSize = 13.sp
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(R.string.sign_up),
                    fontFamily = SFProDisplayRegular,
                    color = Primary600,
                    fontSize = 13.sp,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .clickable {
                            navController.navigate(AuthFeature.RegisterScreen)
                        }
                )
            }
        }
    }
}