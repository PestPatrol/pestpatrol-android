package com.feature.home_presentation.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.core.common.resource.IconArrowForward
import com.core.common.ui.Primary600
import com.core.common.ui.PrimaryGradient200
import com.core.common.ui.PrimaryTextColor
import com.core.common.ui.SFProDisplayMedium
import com.core.common.ui.SFProDisplayRegular
import com.core.common.ui.components.ShimmerBox
import com.feature.home_presentation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewBlog(
    imageUrl: String,
    blogTag: String,
    blogDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                SubcomposeAsyncImage(
                    model = imageUrl,
                    contentDescription = stringResource(R.string.blog_image),
                    contentScale = ContentScale.Crop,
                    loading = {
                        ShimmerBox(
                            modifier = Modifier.fillMaxSize(),
                            roundedCorner = RoundedCornerShape(0.dp)
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp)
                ) {
                    Text(
                        text = blogTag,
                        fontFamily = SFProDisplayMedium,
                        fontSize = 14.sp,
                        color = Color.White,
                        modifier = Modifier
                            .background(
                                brush = PrimaryGradient200,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(vertical = 4.dp, horizontal = 8.dp)
                            .align(Alignment.BottomStart)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp)
            ) {
                Text(
                    text = blogDescription,
                    fontFamily = SFProDisplayMedium,
                    color = PrimaryTextColor,
                    fontSize = 16.sp,
                    lineHeight = 18.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopStart)
                )
                Row(
                    modifier = Modifier.align(Alignment.BottomEnd),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.read_more),
                        fontFamily = SFProDisplayRegular,
                        color = Primary600,
                        fontSize = 14.sp
                    )
                    Icon(
                        painter = painterResource(id = IconArrowForward),
                        contentDescription = stringResource(
                            id = R.string.read_more
                        ),
                        tint = Primary600
                    )
                }
            }
        }
    }
}