package com.feature.auth_data.mapper

import com.feature.auth_data.remote.dto.LoginRequestDto
import com.feature.auth_data.remote.dto.LoginResponseDto
import com.feature.auth_data.remote.dto.RegisterRequestDto
import com.feature.auth_data.remote.dto.RegisterResponseDto
import com.feature.auth_domain.model.LoginRequest
import com.feature.auth_domain.model.LoginResponse
import com.feature.auth_domain.model.RegisterRequest
import com.feature.auth_domain.model.RegisterResponse

fun LoginRequest.toLoginRequestDto() = LoginRequestDto(
    password = this.password,
    email = this.email
)

fun LoginResponseDto.toLoginResponse() = LoginResponse(
    success = this.success,
    message = this.message,
    token = this.data?.token
)

fun RegisterRequest.toRegisterRequestDto() = RegisterRequestDto(
    password = this.password,
    fullName = this.fullName,
    email = this.email
)

fun RegisterResponseDto.toRegisterResponse() = RegisterResponse(
    success = this.success,
    message = this.message
)