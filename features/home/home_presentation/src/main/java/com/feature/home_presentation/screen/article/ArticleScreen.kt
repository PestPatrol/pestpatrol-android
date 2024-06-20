package com.feature.home_presentation.screen.article

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.core.common.resource.IconArrowBack
import com.core.common.resource.IconLoveRounded
import com.core.common.ui.Primary25
import com.core.common.ui.PrimaryScreenBackgroundGradient
import com.core.common.ui.SFProDisplayBold
import com.core.common.ui.components.ShimmerBox
import com.core.common.ui.interaction.disableSplitMotionEvents
import com.core.common.ui.interaction.singleClick
import com.core.common.util.ObserveAsEvents
import com.core.common.util.UiText
import com.feature.home_presentation.R
import com.feature.home_presentation.screen.component.ArticleItem
import com.feature.home_presentation.screen.component.HomeSearchField

@Composable
fun ArticleScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<ArticleViewModel>()
    val context = LocalContext.current

    val articles by viewModel.articleData.collectAsStateWithLifecycle(initialValue = emptyList())
    val isLoading by viewModel.articleLoading.collectAsStateWithLifecycle(initialValue = true)

    ObserveAsEvents(flow = viewModel.articleError) { uiText ->
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
        viewModel.getAllArticles()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryScreenBackgroundGradient)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(24.dp)
        ) {
            IconButton(
                modifier = Modifier
                    .size(36.dp)
                    .align(Alignment.CenterStart)
                    .disableSplitMotionEvents(),
                onClick = singleClick {
                    navController.navigateUp()
                }
            ) {
                Icon(
                    painter = painterResource(id = IconArrowBack),
                    contentDescription = stringResource(id = R.string.back),
                    tint = Color.White,
                    modifier = Modifier.size(36.dp)
                )
            }
            Text(
                text = stringResource(id = R.string.p_blog),
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
                    placeholderText = stringResource(R.string.search),
                    modifier = Modifier.weight(4f)
                )
                Spacer(modifier = Modifier.width(12.dp))
                IconButton(
                    onClick = singleClick {

                    },
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Image(
                        painter = painterResource(id = IconLoveRounded),
                        contentDescription = stringResource(R.string.favorite),
                        modifier = Modifier.size(42.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))



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
                    items(articles) { article ->
                        Spacer(modifier = Modifier.height(12.dp))
                        ArticleItem(
                            article = article,
                            onClick = {

                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                        )
                    }
                item {
                    Spacer(modifier = Modifier.height(72.dp))
                }
            }
        }
    }
}