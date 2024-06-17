package com.feature.home_presentation.screen.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.util.Empty
import com.core.common.util.NetworkConnectivity
import com.feature.home_domain.use_case.HomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val networkConnectivity: NetworkConnectivity,
    private val homeUseCases: HomeUseCases
): ViewModel() {

    val searchQuery = mutableStateOf(String.Empty)

    private val 

    fun getAllArticles() {
        networkConnectivity.checkInternetConnection(object : NetworkConnectivity.ConnectivityCallback {
            override fun onDetected(isConnected: Boolean) {
                if (isConnected) {

                    return
                }
                viewModelScope.launch {

                }
            }
        })
    }
}