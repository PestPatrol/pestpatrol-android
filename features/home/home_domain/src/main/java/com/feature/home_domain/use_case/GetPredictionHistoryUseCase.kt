package com.feature.home_domain.use_case

import com.core.common.util.Resource
import com.core.common.util.UiText
import com.feature.home_domain.model.PredictionHistoryItem
import com.feature.home_domain.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetPredictionHistoryUseCase(
    private val repository: HomeRepository
) {

    operator fun invoke(): Flow<Resource<List<PredictionHistoryItem>>> = flow {
        emit(Resource.Loading())
        try {
            val response = repository.getPredictionHistory()
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(UiText.DynamicString(e.message.toString())))
        }
    }
        .flowOn(Dispatchers.IO)
}