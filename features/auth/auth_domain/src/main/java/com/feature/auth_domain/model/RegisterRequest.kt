package com.feature.auth_domain.model

data class RegisterRequest(
    val fullName: String,
    val email: String,
    val password: String
)