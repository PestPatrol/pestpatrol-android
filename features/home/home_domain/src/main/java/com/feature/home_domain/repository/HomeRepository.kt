package com.feature.home_domain.repository

import com.feature.home_domain.model.Article
import com.feature.home_domain.model.Profile

interface HomeRepository {

    suspend fun getAllArticles(): List<Article>

    suspend fun getProfile(): Profile
}