package com.feature.home_presentation.util

import androidx.compose.ui.graphics.painter.Painter

data class HomeMenuItem(
    val iconPainter: Painter,
    val iconContentDesc: String,
    val menuTitle: String
)
