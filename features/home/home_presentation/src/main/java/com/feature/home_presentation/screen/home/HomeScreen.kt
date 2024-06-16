package com.feature.home_presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.core.common.resource.IconProfileRounded
import com.core.common.resource.ProfileIcon
import com.core.common.ui.Primary25
import com.core.common.ui.Primary500
import com.core.common.ui.Primary600
import com.core.common.ui.PrimaryTextColor
import com.core.common.ui.SFProDisplayBold
import com.core.common.ui.SFProDisplayMedium
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
    val homeMenuItems = listOf(
        HomeMenuItem(
            iconPainter = painterResource(id = R.drawable.ic_snap_detection),
            iconContentDesc = stringResource(R.string.snap_detection),
            menuTitle = stringResource(id = R.string.snap_detection)
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
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.hello_comma),
                        fontFamily = SFProDisplayMedium,
                        color = PrimaryTextColor,
                        fontSize = 24.sp,
                    )
                    Text(
                        text = "Erika Dwi Puspitasari",
                        fontFamily = SFProDisplayBold,
                        color = Primary600,
                        fontSize = 28.sp,
                    )
                }

                Icon(
                    painter = painterResource(id = IconProfileRounded),
                    contentDescription = stringResource(id = ProfileIcon),
                    tint = Primary500,
                    modifier = Modifier.size(50.dp)
                )
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
                            modifier = Modifier.weight(1f)
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
                items(5) {
                    NewBlog(
                        imageUrl = "https://img.etimg.com/thumb/width-1600,height-900,imgsize-98586,resizemode-75,msid-93695051/news/economy/agriculture/paddy-sowing-continues-to-lag-acreage-down-by-8-25-per-cent-till-august-18.jpg",
                        blogTag = "PlantTips $it",
                        blogDescription = "Ini dia tips menanam padi anti gagal panen! $it",
                        onClick = {

                        },
                        modifier = Modifier
                            .width(250.dp)
                            .height(225.dp)
                    )
                }
                item {
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
        }
    }
}