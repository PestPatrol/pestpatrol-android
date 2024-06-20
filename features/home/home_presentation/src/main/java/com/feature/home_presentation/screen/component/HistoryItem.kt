package com.feature.home_presentation.screen.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.core.common.ui.HintTextColor
import com.core.common.ui.Primary600
import com.core.common.ui.RedPrediction
import com.core.common.ui.SFProDisplayMedium
import com.core.common.ui.SFProDisplayRegular
import com.feature.home_domain.model.PredictionHistoryItem
import com.feature.home_presentation.R
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@Composable
fun HistoryItem(
    item: PredictionHistoryItem,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Log.e("OKHT", "LINK: ${item.leafPictureLink}")
            SubcomposeAsyncImage(
                model = item.leafPictureLink,
                contentDescription = stringResource(R.string.history_item_picture),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(1f)
                    .size(50.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .weight(1f),
                loading = {
                    CircularProgressIndicator(
                        color = Primary600,
                        modifier = Modifier.size(48.dp)
                    )
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(3f)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                    ) {
                        Text(
                            text = stringResource(R.string.history_item_title, item.disease),
                            fontFamily = SFProDisplayMedium,
                            fontSize = 16.sp,
                            color = Primary600
                        )
                        Text(
                            text = stringResource(
                                R.string.confidence_score_f,
                                item.confidenceScore
                            ),
                            fontFamily = SFProDisplayRegular,
                            fontSize = 13.sp,
                            color = HintTextColor
                        )
                    }

                    val instant = Instant.parse(item.createdAt)
                    val formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy")
                    val formattedDate = formatter.format(instant.atZone(ZoneOffset.UTC))
                    Text(
                        text = formattedDate,
                        fontFamily = SFProDisplayRegular,
                        fontSize = 12.sp,
                        color = HintTextColor,
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                    )
                }
            }
        }
    }
}