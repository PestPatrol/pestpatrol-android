package com.feature.auth_presentation.screen.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.util.Empty
import com.core.common.util.NetworkConnectivity
import com.core.common.util.Resource
import com.core.common.util.UiText
import com.feature.auth_domain.model.RegisterResponse
import com.feature.auth_domain.use_case.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val networkConnectivity: NetworkConnectivity,
    private val authUseCases: AuthUseCases
): ViewModel() {

    val fullName = mutableStateOf(String.Empty)
    val email = mutableStateOf(String.Empty)
    val password = mutableStateOf(String.Empty)

    private val _registerData = Channel<RegisterResponse?>()
    val registerData = _registerData.receiveAsFlow()
    private val _registerLoading = mutableStateOf(false)
    val registerLoading: State<Boolean> = _registerLoading
    private val _registerError = Channel<UiText>()
    val registerError = _registerError.receiveAsFlow()

    fun register() {
        networkConnectivity.checkInternetConnection(object :
            NetworkConnectivity.ConnectivityCallback {
            override fun onDetected(isConnected: Boolean) {
                if (isConnected) {
                    authUseCases
                        .registerUseCase(
                            fullName = fullName.value,
                            email = email.value,
                            password = password.value,
                        )
                        .onEach { result ->
                            when (result) {
                                is Resource.Error -> {
                                    _registerLoading.value = false
                                    _registerError.send(result.uiText ?: UiText.unknownError())
                                }

                                is Resource.Loading -> {
                                    _registerLoading.value = true
                                }

                                is Resource.Success -> {
                                    _registerLoading.value = false
                                    _registerData.send(result.data)
                                }
                            }
                        }
                        .launchIn(viewModelScope)
                    return
                }
                viewModelScope.launch {
                    _registerError.send(UiText.networkError())
                }
            }
        })
    }
}