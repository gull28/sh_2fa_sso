package com.example.sh_2fa_app.data.api

data class CreateUserResponse(
    val id: String,
    val username: String,
    val totp_secret: String
)

data class CreateUserRequest(
    val username: String
)