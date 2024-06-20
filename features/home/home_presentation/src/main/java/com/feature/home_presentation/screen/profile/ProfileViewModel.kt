package com.feature.home_presentation.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.util.NetworkConnectivity
import com.core.common.util.Resource
import com.core.common.util.UiText
import com.feature.home_domain.model.Profile
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
class ProfileViewModel @Inject constructor(
    private val networkConnectivity: NetworkConnectivity,
    private val homeUseCases: HomeUseCases
) : ViewModel() {

    private val _profileData = Channel<Profile?>()
    val profileData = _profileData.receiveAsFlow()
    private val _profileLoading = MutableStateFlow(true)
    val profileLoading = _profileLoading.asStateFlow()
    private val _profileError = Channel<UiText>()
    val profileError = _profileError.receiveAsFlow()

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

    fun logout() {
        homeUseCases.logoutUseCase().launchIn(viewModelScope)
    }
}