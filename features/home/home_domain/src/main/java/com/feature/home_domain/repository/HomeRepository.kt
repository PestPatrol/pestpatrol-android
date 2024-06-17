package com.feature.home_domain.repository

import com.feature.home_domain.model.Article

interface HomeRepository {

    suspend fun getAllArticles(): List<Article>
}