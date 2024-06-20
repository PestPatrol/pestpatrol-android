package com.feature.home_presentation.screen.snap_history

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.util.Empty
import com.core.common.util.NetworkConnectivity
import com.core.common.util.Resource
import com.core.common.util.UiText
import com.feature.home_domain.model.PredictionHistoryItem
import com.feature.home_domain.use_case.HomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SnapHistoryViewModel @Inject constructor(
    private val networkConnectivity: NetworkConnectivity,
    private val homeUseCases: HomeUseCases
): ViewModel() {

    val searchQuery = mutableStateOf(String.Empty)

    private val _predictionLoading = MutableStateFlow(true)
    val predictionLoading = _predictionLoading.asStateFlow()
    private val _predictionError = Channel<UiText>()
    val predictionError = _predictionError.receiveAsFlow()
    private val _predictionData = Channel<List<PredictionHistoryItem>>()
    val predictionData = _predictionData.receiveAsFlow()

    fun getPredictionHistory() {
        networkConnectivity.checkInternetConnection(object :
            NetworkConnectivity.ConnectivityCallback {
            override fun onDetected(isConnected: Boolean) {
                if (isConnected) {
                    homeUseCases
                        .getPredictionHistoryUseCase()
                        .onEach { result ->
                            when (result) {
                                is Resource.Error -> {
                                    _predictionLoading.value = false
                                    _predictionError.send(result.uiText ?: UiText.unknownError())
                                }

                                is Resource.Loading -> {
                                    _predictionLoading.value = true
                                }

                                is Resource.Success -> {
                                    _predictionLoading.value = false
                                    _predictionData.send(result.data ?: emptyList())
                                }
                            }
                        }
                        .launchIn(viewModelScope)
                    return
                }
                viewModelScope.launch {
                    _predictionError.send(UiText.networkError())
                }
            }
        })
    }
}