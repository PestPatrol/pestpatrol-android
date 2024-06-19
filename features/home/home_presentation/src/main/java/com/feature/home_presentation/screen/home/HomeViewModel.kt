package com.feature.home_presentation.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.util.Empty
import com.core.common.util.NetworkConnectivity
import com.core.common.util.Resource
import com.core.common.util.UiText
import com.feature.home_domain.model.Article
import com.feature.home_domain.model.Profile
import com.feature.home_domain.use_case.HomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val networkConnectivity: NetworkConnectivity,
    private val homeUseCases: HomeUseCases
) : ViewModel() {

    val searchQuery = mutableStateOf(String.Empty)

    private val _articleData = Channel<List<Article>>()
    val articleData = _articleData.receiveAsFlow()
    private val _articleLoading = mutableStateOf(true)
    val articleLoading: State<Boolean> = _articleLoading
    private val _articleError = Channel<UiText>()
    val articleError = _articleError.receiveAsFlow()

    private val _profileData = Channel<Profile?>()
    val profileData = _profileData.receiveAsFlow()
    private val _profileLoading = mutableStateOf(true)
    val profileLoading: State<Boolean> = _profileLoading
    private val _profileError = Channel<UiText>()
    val profileError = _profileError.receiveAsFlow()

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

    fun getProfile() {
        networkConnectivity.checkInternetConnection(object :
            NetworkConnectivity.ConnectivityCallback {
            override fun onDetected(isConnected: Boolean) {
                if (isConnected) {
                    homeUseCases
                        .getProfileUseCase()
                        .onEach { result ->
                            when (result) {
                                is Resource.Error -> {
                                    _profileLoading.value = false
                                    _profileError.send(result.uiText ?: UiText.unknownError())
                                }

                                is Resource.Loading -> {
                                    _profileLoading.value = true
                                }

                                is Resource.Success -> {
                                    _profileLoading.value = false
                                    _profileData.send(result.data)
                                }
                            }
                        }
                        .launchIn(viewModelScope)
                    return
                }
                viewModelScope.launch {
                    _profileError.send(UiText.networkError())
                }
            }
        })
    }
}