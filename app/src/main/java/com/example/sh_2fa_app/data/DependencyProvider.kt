package com.example.sh_2fa_app.data

import android.content.Context
import com.example.sh_2fa_app.data.api.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DependencyProvider {
    private var userPrefs: UserPrefs? = null
    private var apiService: ApiService? = null

    fun provideUserPrefs(context: Context): UserPrefs {
        if (userPrefs == null) {
            userPrefs = UserPrefs(context.applicationContext)
        }
        return userPrefs!!
    }

    fun provideApiService(): ApiService {
        if (apiService == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://example.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            apiService = retrofit.create(ApiService::class.java)
        }
        return apiService!!
    }
}

