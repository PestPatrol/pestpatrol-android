package com.feature.snap_detection_presentation.screen.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.core.common.resource.IconArrowDown
import com.core.common.ui.Primary50
import com.core.common.ui.Primary600
import com.core.common.ui.SFProDisplayRegular
import com.feature.snap_detection.model.CaresItem
import com.feature.snap_detection_presentation.R

@Composable
fun PaddyCareItem(
    item: CaresItem?,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    val rotationState by animateFloatAsState(
        targetValue = if (isExpanded) 180f else 0f,
        label = "rotationState"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(8.dp)
            )
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 500,
                    easing = LinearOutSlowInEasing
                )
            )
    ) {
        Row(
            modifier = modifier
                .background(
                    color = Primary50,
                    shape = RoundedCornerShape(8.dp)
                )
                .clickable {
                    isExpanded = !isExpanded
                }
                .padding(vertical = 8.dp, horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = item?.title.toString(),
                fontFamily = SFProDisplayRegular,
                fontSize = 14.sp,
                color = Primary600,
                modifier = Modifier.weight(6f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                painter = painterResource(id = IconArrowDown),
                contentDescription = stringResource(R.string.dropdown),
                tint = Primary600,
                modifier = Modifier
                    .size(24.dp)
                    .weight(1f)
                    .rotate(rotationState)
            )
        }
        if (isExpanded) {
            Text(
                text = if (item?.detail == null) stringResource(R.string.no_description) else item.detail.toString(),
                fontFamily = SFProDisplayRegular,
                fontSize = 14.sp,
                color = Primary600,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}