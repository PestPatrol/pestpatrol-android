package com.feature.auth_presentation.screen.splash

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.util.NetworkConnectivity
import com.core.common.util.Resource
import com.core.common.util.UiText
import com.feature.auth_domain.use_case.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val networkConnectivity: NetworkConnectivity
) : ViewModel() {

    private val _isTokenAuthorized = Channel<Boolean>()
    val isTokenAuthorized = _isTokenAuthorized.receiveAsFlow()
    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading
    private val _errorMessage = Channel<UiText>()
    val errorMessage = _errorMessage.receiveAsFlow()

    init {
        viewModelScope.launch {
            delay(1000L)
            getToken()
        }
    }

    fun getToken() {
        networkConnectivity.checkInternetConnection(object :
            NetworkConnectivity.ConnectivityCallback {
            override fun onDetected(isConnected: Boolean) {
                if (isConnected) {
                    authUseCases
                        .checkTokenUseCase()
                        .onEach { result ->
                            when (result) {
                                is Resource.Error -> {
                                    _isLoading.value = false
                                    _isTokenAuthorized.send(false)
                                }

                                is Resource.Loading -> {
                                    _isLoading.value = true
                                }

                                is Resource.Success -> {
                                    _isLoading.value = false
                                    _isTokenAuthorized.send(true)
                                }
                            }
                        }
                        .launchIn(viewModelScope)
                    return
                }
                viewModelScope.launch {
                    _errorMessage.send(UiText.networkError())
                    delay(5000)
                    getToken()
                }
            }
        })
    }
}