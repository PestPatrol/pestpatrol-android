package com.feature.snap_detection_presentation.screen.camera

import android.util.Log
import android.widget.Toast
import androidx.camera.core.ImageCapture.OnImageCapturedCallback
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.core.common.resource.IconArrowBack
import com.core.common.resource.IconCameraCenterGrid
import com.core.common.resource.IconInformation
import com.core.common.ui.Primary25
import com.core.common.ui.Primary500
import com.core.common.ui.SFProDisplayRegular
import com.core.common.ui.interaction.disableSplitMotionEvents
import com.core.common.ui.interaction.singleClick
import com.feature.snap_detection_presentation.R
import com.feature.snap_detection_presentation.screen.SnapDetectionViewModel
import com.feature.snap_detection_presentation.screen.prediction_result.PredictionResultScreen

@Composable
fun CameraScreen(
    navController: NavController,
    viewModel: SnapDetectionViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val controller = remember {
        LifecycleCameraController(context).apply {
            setEnabledUseCases(CameraController.IMAGE_CAPTURE)
        }
    }
    var cameraState by remember {
        mutableStateOf(true)
    }

    if (cameraState)
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            CameraPreview(
                controller = controller,
                modifier = Modifier
                    .fillMaxSize()
            )

            IconButton(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(24.dp)
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

            Icon(
                painter = painterResource(id = IconCameraCenterGrid),
                contentDescription = stringResource(
                    R.string.center_grid
                ),
                tint = Color.White,
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
            )

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 36.dp)
                    .offset(y = (-48).dp)
                    .fillMaxWidth()
                    .disableSplitMotionEvents()
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    onClick = singleClick {
                        controller.takePicture(
                            ContextCompat.getMainExecutor(context),
                            object : OnImageCapturedCallback() {
                                override fun onCaptureSuccess(image: ImageProxy) {
                                    super.onCaptureSuccess(image)
                                    Log.e("IMAGE INGFO", "camera: ${image.toBitmap()}")
                                    viewModel.setImageBitmap(image.toBitmap())
                                    viewModel.predict(image.toBitmap())
                                    cameraState = false
                                }

                                override fun onError(exception: ImageCaptureException) {
                                    super.onError(exception)
                                    Toast.makeText(
                                        context,
                                        context.getString(R.string.error_while_taking_a_photo),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        )
                    },
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(8.dp)
                        .clip(CircleShape)
                        .background(Primary500),
                    content = {}
                )

                Column(
                    modifier = Modifier
                        .align(Alignment.CenterEnd),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            painter = painterResource(id = IconInformation),
                            contentDescription = stringResource(R.string.snap_tips),
                            tint = Color.White,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                    Text(
                        text = stringResource(id = R.string.snap_tips),
                        fontFamily = SFProDisplayRegular,
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
            }
        }
    else
        PredictionResultScreen(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .background(Primary25)
                .padding(horizontal = 24.dp),
            viewModel = viewModel
        )
}