package com.feature.auth_domain.model

data class LoginResponse(
    val success: Boolean? = null,
    val message: String? = null,
    val token: String? = null
)