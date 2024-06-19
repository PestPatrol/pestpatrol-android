package com.feature.snap_detection_presentation.screen.choose_image

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import com.core.common.component.findActivity
import com.core.common.resource.IconArrowBack
import com.core.common.resource.IconCamera
import com.core.common.resource.IconImport
import com.core.common.ui.BalooBhaijaanRegular
import com.core.common.ui.Primary25
import com.core.common.ui.Primary600
import com.core.common.ui.PrimaryGradient200
import com.core.common.ui.PrimaryTextColor
import com.core.common.ui.SFProDisplayBold
import com.core.common.ui.SFProDisplayMedium
import com.core.common.ui.SFProDisplayRegular
import com.core.common.ui.interaction.disableSplitMotionEvents
import com.core.common.ui.interaction.singleClick
import com.core.common.util.PermissionUtil
import com.feature.snap_detection_presentation.R

@Composable
fun ChooseImageScreen(
    navController: NavController
) {
    val context = LocalContext.current
    var backButtonState by remember {
        mutableStateOf(true)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary25)
            .padding(24.dp)
    ) {
        IconButton(
            modifier = Modifier
                .padding(top = 48.dp)
                .align(Alignment.TopStart)
                .offset(y = (-48).dp)
                .disableSplitMotionEvents(),
            enabled = backButtonState,
            onClick = singleClick {
                backButtonState = false
                navController.popBackStack()
            }
        ) {
            Icon(
                painter = painterResource(id = IconArrowBack),
                contentDescription = stringResource(id = R.string.back),
                tint = Primary600,
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_choose_predict),
                contentDescription = stringResource(R.string.choose_prediction_image),
                modifier = Modifier
                    .aspectRatio(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = stringResource(R.string.snap_detection),
                fontFamily = BalooBhaijaanRegular,
                color = Primary600,
                fontSize = 32.sp,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.choose_image_description),
                fontFamily = SFProDisplayMedium,
                fontSize = 16.sp,
                color = PrimaryTextColor,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = PrimaryGradient200,
                        shape = ButtonDefaults.shape
                    ),
                onClick = singleClick {
                    if (!PermissionUtil.hasRequiredPermission(
                            context,
                            PermissionUtil.CAMERA_PERMISSION
                        )
                    ) {
                        try {
                            ActivityCompat.requestPermissions(
                                context.findActivity()!!,
                                arrayOf(PermissionUtil.CAMERA_PERMISSION),
                                0
                            )
                        } catch (e: Exception) {
                            Toast.makeText(
                                context,
                                context.getString(R.string.error_failed_to_make_permissions_request),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        return@singleClick
                    }


                }
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = stringResource(R.string.take_a_picture),
                            fontFamily = SFProDisplayBold,
                            color = Color.White,
                            fontSize = 20.sp
                        )
                        Text(
                            text = stringResource(R.string.of_your_plant),
                            fontFamily = SFProDisplayRegular,
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }
                    Icon(
                        painter = painterResource(id = IconCamera),
                        contentDescription = stringResource(R.string.camera),
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = PrimaryGradient200,
                        shape = ButtonDefaults.shape
                    ),
                onClick = singleClick {

                }
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = stringResource(R.string.import_picture),
                            fontFamily = SFProDisplayBold,
                            color = Color.White,
                            fontSize = 20.sp
                        )
                        Text(
                            text = stringResource(R.string.from_your_gallery),
                            fontFamily = SFProDisplayRegular,
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }
                    Icon(
                        painter = painterResource(id = IconImport),
                        contentDescription = stringResource(R.string.camera),
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
    }
}