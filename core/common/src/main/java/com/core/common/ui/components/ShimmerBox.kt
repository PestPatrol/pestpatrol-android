package com.core.common.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.core.common.ui.GrayBasic
import com.valentinilk.shimmer.shimmer

@Composable
fun ShimmerBox(
    modifier: Modifier = Modifier,
    roundedCorner: RoundedCornerShape = RoundedCornerShape(8.dp),
) {
    Box(
        modifier = modifier
            .clip(roundedCorner)
            .shimmer()
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(GrayBasic)
        )
    }
}