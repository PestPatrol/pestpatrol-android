package com.feature.home_presentation.screen.snap_history

import android.widget.Space
import android.widget.Toast
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.core.common.ui.HintTextColor
import com.core.common.ui.HistoryBackgroundGradient
import com.core.common.ui.Primary25
import com.core.common.ui.SFProDisplayBold
import com.core.common.ui.SFProDisplayRegular
import com.core.common.ui.components.ShimmerBox
import com.core.common.util.ObserveAsEvents
import com.core.common.util.UiText
import com.feature.home_presentation.R
import com.feature.home_presentation.screen.component.HistoryItem
import com.feature.home_presentation.screen.component.HomeSearchField

@Composable
fun SnapHistoryScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<SnapHistoryViewModel>()
    val context = LocalContext.current
    val isLoading by viewModel.predictionLoading.collectAsStateWithLifecycle(initialValue = true)
    val historyList by viewModel.predictionData.collectAsStateWithLifecycle(initialValue = emptyList())

    ObserveAsEvents(flow = viewModel.predictionError) { uiText ->
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

    LaunchedEffect(Unit) {
        viewModel.getPredictionHistory()
    }

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
                    .fillMaxSize(),
            ) {
                if (isLoading)
                    items(10) {
                        ShimmerBox(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                else
                    items(
                        historyList,
                        key = {
                            it.id
                        }
                    ) { historyItem ->
                        Spacer(modifier = Modifier.height(12.dp))
                        HistoryItem(
                            item = historyItem,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                        )
                    }
                item {
                    Spacer(modifier = Modifier.height(72.dp))
                }
            }
        }
    }
}