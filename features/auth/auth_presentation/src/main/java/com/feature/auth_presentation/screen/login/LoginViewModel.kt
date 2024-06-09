package com.feature.auth_presentation.screen.login

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.core.common.util.Empty
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

): ViewModel() {

    val username = mutableStateOf(String.Empty)
    val password = mutableStateOf(String.Empty)
    val isButtonEnabled = derivedStateOf {
        username.value.isNotEmpty() && password.value.isNotEmpty()
    }
}