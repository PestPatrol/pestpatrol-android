package com.feature.snap_detection_presentation.screen

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.util.NetworkConnectivity
import com.core.common.util.Resource
import com.core.common.util.UiText
import com.feature.snap_detection.model.PredictResponse
import com.feature.snap_detection.use_case.SnapDetectionUseCases
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
class SnapDetectionViewModel @Inject constructor(
    private val networkConnectivity: NetworkConnectivity,
    private val snapDetectionUseCases: SnapDetectionUseCases
) : ViewModel() {

    private val _imageBitmap = MutableStateFlow<Bitmap?>(null)
    val imageBitmap = _imageBitmap.asStateFlow()

    private val _predictData = Channel<PredictResponse?>()
    val predictData = _predictData.receiveAsFlow()
    private val _predictLoading = MutableStateFlow(false)
    val predictLoading = _predictLoading.asStateFlow()
    private val _predictError = Channel<UiText>()
    val predictError = _predictError.receiveAsFlow()

    fun onTakePhoto(bitmap: Bitmap) {
        _imageBitmap.value = bitmap
    }

    fun predict(bitmap: Bitmap) {
        networkConnectivity.checkInternetConnection(object :
            NetworkConnectivity.ConnectivityCallback {
            override fun onDetected(isConnected: Boolean) {
                if (isConnected) {
                    snapDetectionUseCases
                        .predictUseCase(bitmap)
                        .onEach { result ->
                            when (result) {
                                is Resource.Error -> {
                                    _predictLoading.value = false
                                    _predictError.send(result.uiText ?: UiText.unknownError())
                                }

                                is Resource.Loading -> {
                                    _predictLoading.value = true
                                }

                                is Resource.Success -> {
                                    _predictLoading.value = false
                                    _predictData.send(result.data)
                                }
                            }
                        }
                        .launchIn(viewModelScope)
                    return
                }
                viewModelScope.launch {
                    _predictError.send(UiText.networkError())
                }
            }
        })
    }
}