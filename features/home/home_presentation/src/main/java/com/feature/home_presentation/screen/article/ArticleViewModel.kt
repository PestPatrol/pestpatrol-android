package com.feature.home_presentation.screen.article

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.util.Empty
import com.core.common.util.NetworkConnectivity
import com.core.common.util.Resource
import com.core.common.util.UiText
import com.feature.home_domain.model.Article
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
class ArticleViewModel @Inject constructor(
    private val networkConnectivity: NetworkConnectivity,
    private val homeUseCases: HomeUseCases
): ViewModel() {

    val searchQuery = mutableStateOf(String.Empty)

    private val _articleData = Channel<List<Article>>()
    val articleData = _articleData.receiveAsFlow()
    private val _articleLoading = MutableStateFlow(true)
    val articleLoading = _articleLoading.asStateFlow()
    private val _articleError = Channel<UiText>()
    val articleError = _articleError.receiveAsFlow()

    fun getAllArticles() {
        networkConnectivity.checkInternetConnection(object :
            NetworkConnectivity.ConnectivityCallback {
            override fun onDetected(isConnected: Boolean) {
                if (isConnected) {
                    homeUseCases
                        .getAllArticlesUseCase()
                        .onEach { result ->
                            when (result) {
                                is Resource.Error -> {
                                    _articleLoading.value = false
                                    _articleError.send(result.uiText ?: UiText.unknownError())
                                }

                                is Resource.Loading -> {
                                    _articleLoading.value = true
                                }

                                is Resource.Success -> {
                                    _articleLoading.value = false
                                    _articleData.send(result.data ?: emptyList())
                                }
                            }
                        }
                        .launchIn(viewModelScope)
                    return
                }
                viewModelScope.launch {
                    _articleError.send(UiText.networkError())
                }
            }
        })
    }
}