package com.feature.auth_domain.model

data class LoginRequest(
    val email: String,
    val password: String
)