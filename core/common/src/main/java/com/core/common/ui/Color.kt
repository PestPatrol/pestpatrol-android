package com.core.common.ui

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val Primary25 = Color(0xFFF9FFE2)
val Primary50 = Color(0xFFD2F686)
val Primary100 = Color(0xFFBFE434)
val Primary200 = Color(0xFF8BC10F)
val Primary300 = Color(0xFF76B200)
val Primary400 = Color(0xFF67B929)
val Primary500 = Color(0xFF54B435)
val Primary600 = Color(0xFF3A791D)

val RedPrediction = Color(0xFFFF3D00)

val PrimaryGradient100 = Brush.linearGradient(
    colors = listOf(
        Primary500,
        Primary600
    )
)
val PrimaryGradient200 = Brush.linearGradient(
    colors = listOf(
        Primary500,
        Primary200
    )
)
val PrimaryGradient300 = Brush.linearGradient(
    colors = listOf(
        Primary100,
        Primary500
    )
)
val PrimaryGradient400 = Brush.linearGradient(
    colors = listOf(
        Primary500,
        Primary100
    )
)
val PrimaryScreenBackgroundGradient = Brush.verticalGradient(
    colors = listOf(
        Primary500,
        Primary100,
        Color.Transparent,
        Color.Transparent,
        Color.Transparent,
        Color.Transparent,
    )
)

val PrimaryTextColor = Color(0xFF242424)
val HintTextColor = Color(0xFFB4B4B4)
val GrayBasic = Color(0xFF808080)