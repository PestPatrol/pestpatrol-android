package com.feature.auth_presentation.screen.splash

import androidx.lifecycle.ViewModel
import com.core.common.util.NetworkConnectivity
import com.feature.auth_domain.use_case.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
): ViewModel() {

    
}