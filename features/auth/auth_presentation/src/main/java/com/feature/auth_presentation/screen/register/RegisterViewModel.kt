package com.feature.auth_presentation.screen.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.core.common.util.Empty
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(

): ViewModel() {

    val fullName = mutableStateOf(String.Empty)
    val username = mutableStateOf(String.Empty)
    val password = mutableStateOf(String.Empty)
}