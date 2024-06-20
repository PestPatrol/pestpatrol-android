package com.feature.snap_detection_presentation.screen.gallery

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.core.common.resource.IconArrowBack
import com.core.common.ui.Primary25
import com.core.common.ui.Primary50
import com.core.common.ui.Primary500
import com.core.common.ui.Primary600
import com.core.common.ui.PrimaryTextColor
import com.core.common.ui.RedPrediction
import com.core.common.ui.SFProDisplayBold
import com.core.common.ui.SFProDisplayMedium
import com.core.common.ui.SFProDisplayRegular
import com.core.common.ui.components.ShimmerBox
import com.core.common.ui.interaction.disableSplitMotionEvents
import com.core.common.ui.interaction.singleClick
import com.core.common.util.ObserveAsEvents
import com.core.common.util.UiText
import com.feature.snap_detection_presentation.R
import com.feature.snap_detection_presentation.screen.SnapDetectionViewModel
import com.feature.snap_detection_presentation.screen.component.PaddyCareItem

@Composable
fun PredictionGalleryScreen(
    navController: NavController,
    viewModel: SnapDetectionViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val image by viewModel.imageUri.collectAsStateWithLifecycle(initialValue = null)
    val predictData by viewModel.predictData.collectAsStateWithLifecycle(initialValue = null)
    val predictLoading by viewModel.predictLoading.collectAsStateWithLifecycle(initialValue = false)
    var predictState by remember {
        mutableStateOf(false)
    }
    val healthyStatus = predictData?.disease?.contains(
        "Healthy",
        true
    ) == true

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
        it?.let {
            viewModel.setImageUri(it)
            viewModel.predict(it)
            predictState = true
        }
    }

    ObserveAsEvents(flow = viewModel.predictError) { uiText ->
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

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary25)
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(24.dp))
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .disableSplitMotionEvents(),
                    onClick = singleClick {
                        navController.navigateUp()
                    }
                ) {
                    Icon(
                        painter = painterResource(id = IconArrowBack),
                        contentDescription = stringResource(id = R.string.back),
                        tint = Primary600,
                        modifier = Modifier.size(36.dp)
                    )
                }
                Text(
                    text = stringResource(R.string.result),
                    fontFamily = SFProDisplayBold,
                    fontSize = 20.sp,
                    color = Primary600,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        item {
            if (image != null)
                AsyncImage(
                    model = image,
                    contentDescription = stringResource(R.string.prediction_image),
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .height(250.dp),
                    contentScale = ContentScale.Crop
                )
            else
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .background(
                            Color.White,
                            RoundedCornerShape(16.dp)
                        )
                        .border(
                            2.dp, Primary600, RoundedCornerShape(16.dp)
                        )
                        .clickable {
                            galleryLauncher.launch("image/*")
                        }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = stringResource(R.string.choose_picture_from_gallery),
                        tint = Primary600,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .fillParentMaxSize()
                            .padding(64.dp),
                    )
                }
        }

        if (predictState) {
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.disease_status),
                    color = PrimaryTextColor,
                    fontSize = 16.sp,
                    fontFamily = SFProDisplayMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                if (predictLoading)
                    ShimmerBox(
                        modifier = Modifier
                            .width(150.dp)
                            .height(24.dp)
                    )
                else
                    Text(
                        text = if (healthyStatus) stringResource(R.string.healthy_no_disease) else stringResource(
                            R.string.disease_identified
                        ),
                        fontFamily = SFProDisplayRegular,
                        fontSize = 14.sp,
                        color = Color.White,
                        modifier = Modifier
                            .background(
                                color = if (healthyStatus) Primary500 else RedPrediction,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(vertical = 4.dp, horizontal = 8.dp)
                    )
            }
            if (!healthyStatus && !predictLoading)
                item {
                    Text(
                        text = stringResource(R.string.type_of_disease),
                        color = PrimaryTextColor,
                        fontSize = 16.sp,
                        fontFamily = SFProDisplayMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = predictData?.disease.toString(),
                        fontFamily = SFProDisplayRegular,
                        fontSize = 14.sp,
                        color = Primary600,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = Primary50,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(vertical = 8.dp, horizontal = 8.dp)
                    )
                }
            item {
                Text(
                    text = stringResource(R.string.confidence),
                    color = PrimaryTextColor,
                    fontSize = 16.sp,
                    fontFamily = SFProDisplayMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                if (predictLoading)
                    ShimmerBox(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp)
                    )
                else
                    Text(
                        text = predictData?.confidenceScore.toString(),
                        fontFamily = SFProDisplayRegular,
                        fontSize = 14.sp,
                        color = Primary600,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = Primary50,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(vertical = 8.dp, horizontal = 8.dp)
                    )
            }
            if (!healthyStatus && !predictLoading)
                item {
                    Text(
                        text = stringResource(R.string.cause_of_the_disease),
                        color = PrimaryTextColor,
                        fontSize = 16.sp,
                        fontFamily = SFProDisplayMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = predictData?.disease.toString(),
                        fontFamily = SFProDisplayRegular,
                        fontSize = 14.sp,
                        color = Primary600,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = Primary50,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(vertical = 8.dp, horizontal = 8.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(R.string.imprecise_cultural_techniques_colon),
                        fontFamily = SFProDisplayMedium,
                        fontSize = 15.sp,
                        color = Primary600,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 36.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    for (item in predictData?.impreciseTechniques ?: emptyList()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                model = item?.pictureLink,
                                contentDescription = stringResource(R.string.imprecise_cultural_techniques),
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.size(36.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = item?.title.toString(),
                                fontFamily = SFProDisplayRegular,
                                fontSize = 14.sp,
                                color = Primary600,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        color = Primary50,
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .padding(vertical = 8.dp, horizontal = 8.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }
            if (predictLoading)
                item {
                    ShimmerBox(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp)
                    )
                }
            if (predictData?.cares?.isEmpty() == false && !predictLoading)
                item {
                    Text(
                        text = stringResource(R.string.paddy_care),
                        color = PrimaryTextColor,
                        fontSize = 16.sp,
                        fontFamily = SFProDisplayMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    for (item in predictData?.cares ?: emptyList()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                model = item?.pictureLink,
                                contentDescription = stringResource(R.string.imprecise_cultural_techniques),
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.size(36.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            PaddyCareItem(
                                item = item,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }
            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}