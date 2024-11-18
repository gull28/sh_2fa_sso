package com.example.sh_2fa_app.data

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UserPrefsViewModelFactory(private val context: Context) : ViewModelProvider.Factory  {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PrefsViewModel::class.java)) {
            val userPrefs = DependencyProvider.provideUserPrefs(context)
            val apiService = DependencyProvider.provideApiService()

            @Suppress("UNCHECKED_CAST")
            return PrefsViewModel(userPrefs, apiService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}