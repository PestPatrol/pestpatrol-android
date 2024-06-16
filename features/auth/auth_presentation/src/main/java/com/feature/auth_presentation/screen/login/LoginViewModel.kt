package com.feature.auth_presentation.screen.login

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.util.Empty
import com.core.common.util.NetworkConnectivity
import com.core.common.util.Resource
import com.core.common.util.UiText
import com.feature.auth_domain.model.LoginResponse
import com.feature.auth_domain.use_case.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val networkConnectivity: NetworkConnectivity,
    private val authUseCases: AuthUseCases
) : ViewModel() {

    val email = mutableStateOf(String.Empty)
    val password = mutableStateOf(String.Empty)
    val isButtonEnabled = derivedStateOf {
        email.value.isNotEmpty() && password.value.isNotEmpty()
    }

    private val _loginData = Channel<LoginResponse?>()
    val loginData = _loginData.receiveAsFlow()
    private val _loginLoading = mutableStateOf(false)
    val loginLoading: State<Boolean> = _loginLoading
    private val _loginError = Channel<UiText>()
    val loginError = _loginError.receiveAsFlow()

    fun login() {
        networkConnectivity.checkInternetConnection(object :
            NetworkConnectivity.ConnectivityCallback {
            override fun onDetected(isConnected: Boolean) {
                if (isConnected) {
                    authUseCases
                        .loginUseCase(
                            email = email.value,
                            password = password.value
                        )
                        .onEach { result ->
                            when (result) {
                                is Resource.Error -> {
                                    _loginLoading.value = false
                                    _loginError.send(result.uiText ?: UiText.unknownError())
                                }

                                is Resource.Loading -> {
                                    _loginLoading.value = true
                                }

                                is Resource.Success -> {
                                    _loginLoading.value = false
                                    _loginData.send(result.data)
                                }
                            }
                        }
                        .launchIn(viewModelScope)
                    return
                }
                viewModelScope.launch {
                    _loginError.send(UiText.networkError())
                }
            }
        })
    }
}