package com.feature.home_data.mapper

import com.feature.home_data.remote.dto.GetAllArticlesResponseDto
import com.feature.home_domain.model.Article

fun GetAllArticlesResponseDto.toListOfArticle() = this.data?.map {
    Article(
        articleId = it?.articleId.toString(),
        nanoseconds = it?.data?.createdAt?.nanoseconds ?: -1,
        seconds = it?.data?.createdAt?.seconds ?: -1,
        pictureLink = it?.data?.pictureLink.toString(),
        category = it?.data?.category.toString(),
        title = it?.data?.title.toString(),
        content = it?.data?.content.toString()
    )
} ?: emptyList()