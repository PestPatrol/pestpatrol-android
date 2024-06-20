package com.feature.home_domain.repository

import com.feature.home_domain.model.Article
import com.feature.home_domain.model.PredictionHistoryItem
import com.feature.home_domain.model.Profile

interface HomeRepository {

    suspend fun getAllArticles(): List<Article>

    suspend fun getArticleById(articleId: String): Article

    suspend fun getProfile(): Profile

    suspend fun getPredictionHistory(): List<PredictionHistoryItem>

    suspend fun logout()
}