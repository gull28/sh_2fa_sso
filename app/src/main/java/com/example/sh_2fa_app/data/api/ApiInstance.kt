package com.example.sh_2fa_app.data.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @POST
    suspend fun createUser(@Url url: String, @Body username: CreateUserRequest): Response<CreateUserResponse>
}
