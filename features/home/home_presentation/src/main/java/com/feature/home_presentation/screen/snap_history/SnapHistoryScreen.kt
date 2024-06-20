package com.feature.home_presentation.screen.snap_history

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.core.common.ui.HintTextColor
import com.core.common.ui.HistoryBackgroundGradient
import com.core.common.ui.Primary25
import com.core.common.ui.SFProDisplayBold
import com.core.common.ui.SFProDisplayRegular
import com.feature.home_presentation.R
import com.feature.home_presentation.screen.component.HomeSearchField

@Composable
fun SnapHistoryScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<SnapHistoryViewModel>()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(HistoryBackgroundGradient)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(24.dp)
        ) {
            Text(
                text = stringResource(id = R.string.snap_history),
                fontFamily = SFProDisplayBold,
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.BottomCenter),
                color = Color.White
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Primary25,
                    RoundedCornerShape(
                        topStart = 48.dp,
                        topEnd = 48.dp
                    )
                )
                .padding(24.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HomeSearchField(
                    value = viewModel.searchQuery.value,
                    onValueChange = {
                        viewModel.searchQuery.value = it
                    },
                    placeholderText = stringResource(R.string.search_disease),
                    modifier = Modifier.weight(2f)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    shape = CircleShape,
                    border = BorderStroke(1.dp, HintTextColor),
                    onClick = {

                    },
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = stringResource(R.string.select),
                        color = HintTextColor,
                        fontSize = 14.sp,
                        fontFamily = SFProDisplayRegular
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {

            }
        }
    }
}