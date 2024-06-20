package com.feature.home_presentation.screen.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.core.common.navigation_constants.SnapDetectionFeature
import com.core.common.resource.IconProfileRounded
import com.core.common.resource.ProfileIconDescription
import com.core.common.ui.Primary25
import com.core.common.ui.Primary600
import com.core.common.ui.PrimaryTextColor
import com.core.common.ui.SFProDisplayBold
import com.core.common.ui.SFProDisplayMedium
import com.core.common.ui.components.ShimmerBox
import com.core.common.util.ObserveAsEvents
import com.core.common.util.UiText
import com.feature.home_presentation.R
import com.feature.home_presentation.screen.component.HomeMenu
import com.feature.home_presentation.screen.component.HomeSearchField
import com.feature.home_presentation.screen.component.NewBlog
import com.feature.home_presentation.util.HomeMenuItem

@Composable
fun HomeScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val context = LocalContext.current
    val homeMenuItems = listOf(
        HomeMenuItem(
            iconPainter = painterResource(id = R.drawable.ic_snap_detection),
            iconContentDesc = stringResource(R.string.snap_detection),
            menuTitle = stringResource(id = R.string.snap_detection),
            onClick = {
                navController.navigate(SnapDetectionFeature.NestedRoute)
            }
        ),
        HomeMenuItem(
            iconPainter = painterResource(id = R.drawable.ic_reminder),
            iconContentDesc = stringResource(R.string.reminder),
            menuTitle = stringResource(id = R.string.reminder)
        ),
        HomeMenuItem(
            iconPainter = painterResource(id = R.drawable.ic_p_blog),
            iconContentDesc = stringResource(R.string.p_blog),
            menuTitle = stringResource(id = R.string.p_blog)
        ),
        HomeMenuItem(
            iconPainter = painterResource(id = R.drawable.ic_paddy_buddy),
            iconContentDesc = stringResource(R.string.paddy_buddy),
            menuTitle = stringResource(id = R.string.paddy_buddy)
        ),
    )
    val profile by viewModel.profileData.collectAsStateWithLifecycle(initialValue = null)
    val articles by viewModel.articleData.collectAsStateWithLifecycle(initialValue = emptyList())

    LaunchedEffect(Unit) {
        viewModel.getAllArticles()
        viewModel.getProfile()
    }

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

    ObserveAsEvents(flow = viewModel.profileError) { uiText ->
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

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary25),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        state = rememberLazyListState()
    ) {
        item {
            Spacer(modifier = Modifier.height(48.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.weight(3f)
                ) {
                    Text(
                        text = stringResource(R.string.hello_comma),
                        fontFamily = SFProDisplayMedium,
                        color = PrimaryTextColor,
                        fontSize = 24.sp,
                        lineHeight = 28.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    if (profile == null)
                        ShimmerBox(
                            modifier = Modifier
                                .height(32.dp)
                                .width(150.dp)
                        )
                    else
                        Text(
                            text = profile?.fullName.toString(),
                            fontFamily = SFProDisplayBold,
                            color = Primary600,
                            fontSize = 28.sp,
                            lineHeight = 30.sp
                        )
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                ) {
                    if (profile == null)
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(50.dp)
                                .align(Alignment.CenterEnd),
                            color = Primary600
                        )
                    else
                        SubcomposeAsyncImage(
                            model = profile?.profPicLink,
                            contentDescription = stringResource(id = ProfileIconDescription),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .aspectRatio(1f)
                                .size(50.dp)
                                .clip(CircleShape)
                                .align(Alignment.CenterEnd),
                            loading = {
                                CircularProgressIndicator(
                                    color = Primary600,
                                    modifier = Modifier
                                        .size(50.dp)
                                        .align(Alignment.CenterEnd)
                                )
                            },
                            error = {
                                Icon(
                                    painter = painterResource(id = IconProfileRounded),
                                    contentDescription =
                                    stringResource(id = ProfileIconDescription),
                                    tint = Primary600,
                                    modifier = Modifier
                                        .size(50.dp)
                                        .align(Alignment.CenterEnd)
                                )
                            }
                        )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            HomeSearchField(
                value = viewModel.searchQuery.value,
                onValueChange = {
                    viewModel.searchQuery.value = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            )
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
            ) {
                Text(
                    text = stringResource(R.string.menu),
                    fontFamily = SFProDisplayBold,
                    fontSize = 20.sp,
                    color = PrimaryTextColor
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    homeMenuItems.forEach { menuItem ->
                        HomeMenu(
                            iconPainter = menuItem.iconPainter,
                            iconContentDescription = menuItem.iconContentDesc,
                            menuTitle = menuItem.menuTitle,
                            modifier = Modifier.weight(1f),
                            onClick = menuItem.onClick
                        )
                    }
                }
            }
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.new_blogs),
                    fontFamily = SFProDisplayBold,
                    fontSize = 20.sp,
                    color = PrimaryTextColor
                )
                Text(
                    text = stringResource(R.string.see_all),
                    fontFamily = SFProDisplayMedium,
                    fontSize = 16.sp,
                    color = Primary600,
                    modifier = Modifier
                        .clickable {

                        }
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                state = rememberLazyListState(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.width(16.dp))
                }
                if (articles.isEmpty())
                    items(5) {
                        ShimmerBox(
                            modifier = Modifier
                                .width(275.dp)
                                .height(225.dp),
                            roundedCorner = RoundedCornerShape(16.dp)
                        )
                    }
                else
                    items(articles) { article ->
                        NewBlog(
                            imageUrl = article.pictureLink,
                            blogTag = article.category,
                            blogDescription = article.content,
                            onClick = {

                            },
                            modifier = Modifier
                                .width(275.dp)
                                .height(225.dp),
                        )
                    }
                item {
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}