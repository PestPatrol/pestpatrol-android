package com.feature.home_data.mapper

import com.feature.home_data.remote.dto.ArticleDetailResponseDto
import com.feature.home_data.remote.dto.GetAllArticlesResponseDto
import com.feature.home_data.remote.dto.PredictionHistoryResponseDto
import com.feature.home_data.remote.dto.ProfileResponseDto
import com.feature.home_domain.model.Article
import com.feature.home_domain.model.PredictionHistoryItem
import com.feature.home_domain.model.Profile

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

fun ProfileResponseDto.toProfile() = Profile(
    fullName = this.fullName.toString(),
    userId = this.userId.toString(),
    email = this.email.toString(),
    profPicLink = this.profPicLink.toString()
)

fun PredictionHistoryResponseDto.toListOfPredictionHistoryItem() = PredictionHistoryItem(
    leafPictureLink = this.leafPictureLink.toString(),
    createdAt = this.createdAt.toString(),
    confidenceScore = this.confidenceScore ?: -1.0,
    disease = this.disease.toString()
)

fun ArticleDetailResponseDto.toArticle() = Article(
    articleId = this.articleId.toString(),
    nanoseconds = this.createdAt?.nanoseconds ?: -1,
    seconds = this.createdAt?.seconds ?: -1,
    pictureLink = this.pictureLink.toString(),
    category = this.category.toString(),
    title = this.title.toString(),
    content = this.content.toString()
)