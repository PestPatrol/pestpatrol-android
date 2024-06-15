package com.feature.home_presentation.screen.snap_history

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.core.common.util.Empty
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SnapHistoryViewModel @Inject constructor(

): ViewModel() {

    val searchQuery = mutableStateOf(String.Empty)
}