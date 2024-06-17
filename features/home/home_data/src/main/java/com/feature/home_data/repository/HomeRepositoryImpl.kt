package com.feature.home_data.repository

import com.core.common.util.formatBearerToken
import com.core.network.data.GlobalErrorParser
import com.core.network.data_store.UserDataStore
import com.feature.home_data.mapper.toListOfArticle
import com.feature.home_data.remote.network.HomeService
import com.feature.home_domain.model.Article
import com.feature.home_domain.repository.HomeRepository
import kotlinx.coroutines.flow.first

class HomeRepositoryImpl(
    private val homeService: HomeService,
    private val globalErrorParser: GlobalErrorParser,
    private val userDataStore: UserDataStore
) : HomeRepository {

    override suspend fun getAllArticles(): List<Article> {
        val token = userDataStore.getToken().first()
        val response = homeService.getAllArticles(token.formatBearerToken())
        if (response.isSuccessful) {
            response.body()?.let { data -> return data.toListOfArticle() }
        }
        val error = globalErrorParser.parse(response.errorBody()?.string())
        throw Exception(error)
    }
}