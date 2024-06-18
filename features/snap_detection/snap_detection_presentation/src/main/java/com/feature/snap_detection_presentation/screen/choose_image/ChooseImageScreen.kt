package com.feature.snap_detection_presentation.screen.choose_image

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.core.common.resource.IconArrowBack
import com.core.common.ui.Primary25
import com.core.common.ui.Primary600
import com.feature.snap_detection_presentation.R

@Composable
fun ChooseImageScreen(

) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary25)
            .padding(16.dp)
    ) {
        Icon(
            painter = painterResource(id = IconArrowBack),
            contentDescription = stringResource(id = R.string.back),
            tint = Primary600,
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(y = (-48).dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_choose_predict),
                contentDescription = stringResource(R.string.choose_prediction_image)
            )
        }
    }
}