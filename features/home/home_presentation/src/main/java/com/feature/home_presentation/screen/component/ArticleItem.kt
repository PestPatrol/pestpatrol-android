package com.feature.home_presentation.screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.core.common.ui.Primary600
import com.core.common.ui.PrimaryTextColor
import com.core.common.ui.SFProDisplayMedium
import com.core.common.ui.SFProDisplayRegular
import com.feature.home_domain.model.Article
import com.feature.home_presentation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleItem(
    article: Article,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = {
            onClick(article.articleId)
        },
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SubcomposeAsyncImage(
                model = article.pictureLink,
                contentDescription = stringResource(R.string.blog_image),
                contentScale = ContentScale.Crop,
                loading = {
                    CircularProgressIndicator(color = Primary600)
                },
                modifier = Modifier
                    .weight(2f)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(3f)
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = article.title,
                    fontFamily = SFProDisplayMedium,
                    fontSize = 16.sp,
                    color = PrimaryTextColor,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = stringResource(id = R.string.read_more),
                    fontFamily = SFProDisplayRegular,
                    fontSize = 14.sp,
                    color = Primary600
                )
            }
        }
    }
}