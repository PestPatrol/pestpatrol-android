package com.core.common.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.core.common.R
import com.core.common.ui.BalooBhaijaanRegular
import com.core.common.ui.Primary25
import com.core.common.ui.Primary600

@Composable
fun ComingSoonScreen(
    navController: NavController
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Primary25)
    ) {
        Text(
            text = stringResource(R.string.coming_soon),
            fontFamily = BalooBhaijaanRegular,
            fontSize = 48.sp,
            color = Primary600,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}