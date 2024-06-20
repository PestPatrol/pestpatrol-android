package com.feature.home_data.repository

import coil.request.NullRequestDataException
import com.core.common.util.formatBearerToken
import com.core.network.data.GlobalErrorParser
import com.core.network.data_store.UserDataStore
import com.feature.home_data.mapper.toListOfArticle
import com.feature.home_data.mapper.toListOfPredictionHistoryItem
import com.feature.home_data.mapper.toProfile
import com.feature.home_data.remote.network.HomeService
import com.feature.home_domain.model.Article
import com.feature.home_domain.model.PredictionHistoryItem
import com.feature.home_domain.model.Profile
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

    override suspend fun getProfile(): Profile {
        val token = userDataStore.getToken().first()
        val response = homeService.getProfile(token.formatBearerToken())
        if (response.isSuccessful) {
            response.body()
                ?.let { data -> return data.data?.toProfile() ?: throw NullRequestDataException() }
        }
        val error = globalErrorParser.parse(response.errorBody()?.string())
        throw Exception(error)
    }

    override suspend fun getPredictionHistory(): List<PredictionHistoryItem> {
        val token = userDataStore.getToken().first()
        val response = homeService.getPredictionHistory(token.formatBearerToken())
        if (response.isSuccessful) {
            response.body()
                ?.let { data ->
                    return data.data?.map {
                        it.toListOfPredictionHistoryItem()
                    } ?: throw NullRequestDataException()
                }
        }
        val error = globalErrorParser.parse(response.errorBody()?.string())
        throw Exception(error)
    }
}