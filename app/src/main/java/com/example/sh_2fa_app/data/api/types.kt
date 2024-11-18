package com.example.sh_2fa_app.data.api

data class CreateUserResponse(
    val id: Int,
    val username: String,
    val totpSecret: String
)

data class CreateUserRequest(
    val username: String
)