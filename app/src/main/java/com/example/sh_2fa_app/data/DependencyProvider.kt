package com.example.sh_2fa_app.data

import android.content.Context

object DependencyProvider {
    private var userPrefs: UserPrefs? = null

    fun provideUserPrefs(context: Context): UserPrefs {
        if (userPrefs == null) {
            // Use applicationContext to prevent memory leaks
            userPrefs = UserPrefs(context.applicationContext)
        }
        return userPrefs!!
    }
}

