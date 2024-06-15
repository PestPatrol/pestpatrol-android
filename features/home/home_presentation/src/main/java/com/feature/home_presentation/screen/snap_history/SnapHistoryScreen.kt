package com.feature.home_presentation.screen.snap_history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.core.common.ui.Primary25
import com.core.common.ui.PrimaryGradient300
import com.core.common.ui.SFProDisplayBold
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
            .background(PrimaryGradient300)
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
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Primary25)
                .clip(
                    RoundedCornerShape(
                        topStart = 48.dp,
                        topEnd = 48.dp
                    )
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(3f)
            ) {
                HomeSearchField(
                    value = viewModel.searchQuery.value,
                    onValueChange = {
                        viewModel.searchQuery.value = it
                    },
                    placeholderText = stringResource(R.string.search_disease)
                )

                Button(
                    onClick = {

                    },
                ) {

                }
            }
        }
    }
}