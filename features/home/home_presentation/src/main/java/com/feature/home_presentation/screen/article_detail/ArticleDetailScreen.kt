package com.feature.home_presentation.screen.article_detail

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.core.common.resource.IconArrowBack
import com.core.common.ui.Primary25
import com.core.common.ui.PrimaryGradient200
import com.core.common.ui.PrimaryScreenBackgroundGradient
import com.core.common.ui.PrimaryTextColor
import com.core.common.ui.SFProDisplayBold
import com.core.common.ui.SFProDisplayMedium
import com.core.common.ui.SFProDisplayRegular
import com.core.common.ui.components.LoadingDialog
import com.core.common.ui.components.ShimmerBox
import com.core.common.ui.interaction.disableSplitMotionEvents
import com.core.common.ui.interaction.singleClick
import com.core.common.util.ObserveAsEvents
import com.core.common.util.UiText
import com.feature.home_presentation.R

@Composable
fun ArticleDetailScreen(
    articleId: String,
    navController: NavController
) {
    val viewModel = hiltViewModel<ArticleDetailViewModel>()
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val article by viewModel.articleData.collectAsStateWithLifecycle(initialValue = null)
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
        viewModel.getArticleById(articleId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryScreenBackgroundGradient)
            .verticalScroll(scrollState)
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
                text = stringResource(R.string.p_blog_detail),
                fontFamily = SFProDisplayBold,
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.Center),
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
            if (isLoading) LoadingDialog()
            else {
                SubcomposeAsyncImage(
                    model = article?.pictureLink,
                    contentDescription = stringResource(R.string.blog_image),
                    contentScale = ContentScale.Crop,
                    loading = {
                        ShimmerBox(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                        )
                    },
                    modifier = Modifier
                        .clip(RoundedCornerShape(24.dp))
                        .height(250.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Color.White,
                            RoundedCornerShape(48.dp)
                        )
                        .padding(16.dp)
                ) {
                    Text(
                        text = article?.title.toString(),
                        fontFamily = SFProDisplayBold,
                        fontSize = 24.sp,
                        color = PrimaryTextColor
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = article?.category.toString(),
                        fontFamily = SFProDisplayMedium,
                        fontSize = 16.sp,
                        color = Color.White,
                        modifier = Modifier
                            .background(
                                brush = PrimaryGradient200,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(vertical = 4.dp, horizontal = 8.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = article?.content.toString().replace("\\n", "\n"),
                        fontFamily = SFProDisplayRegular,
                        fontSize = 16.sp,
                        color = PrimaryTextColor,
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Justify,
                    )
                }
            }
        }
    }
}