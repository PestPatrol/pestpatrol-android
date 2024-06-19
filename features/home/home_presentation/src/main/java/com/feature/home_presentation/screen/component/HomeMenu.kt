package com.feature.home_presentation.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.core.common.ui.Primary200
import com.core.common.ui.Primary500
import com.core.common.ui.PrimaryTextColor
import com.core.common.ui.SFProDisplayMedium

@Composable
fun HomeMenu(
    iconPainter: Painter,
    iconContentDescription: String,
    menuTitle: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Primary200,
                            Primary500
                        )
                    ),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(16.dp)
                .clickable(onClick = onClick)
        ) {
            Icon(
                painter = iconPainter,
                contentDescription = iconContentDescription,
                tint = Color.White,
                modifier = Modifier
                    .aspectRatio(1f)
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = menuTitle,
            fontFamily = SFProDisplayMedium,
            fontSize = 12.sp,
            color = PrimaryTextColor,
            lineHeight = 12.sp,
            textAlign = TextAlign.Center
        )
    }
}