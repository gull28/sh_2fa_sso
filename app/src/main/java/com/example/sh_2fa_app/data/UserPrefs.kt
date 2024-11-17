package com.example.sh_2fa_app.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_preferences")

class UserPrefs(context: Context) {
    private val dataStore = context.applicationContext.dataStore // Use applicationContext

    companion object {
        val ENDPOINT_KEY = stringPreferencesKey("endpoint")
        val USERNAME_KEY = stringPreferencesKey("username")
        val USER_ID_KEY = stringPreferencesKey("user_id_key")
        val TOTP_SECRET_KEY = stringPreferencesKey("totp_secret")
    }

    suspend fun saveEndpointKey(endpoint: String) {
        dataStore.edit { prefs ->
            prefs[ENDPOINT_KEY] = endpoint
        }
    }
    suspend fun saveUserId(userId: String) {
        dataStore.edit { prefs ->
            prefs[USER_ID_KEY] = userId
        }
    }

    suspend fun saveUsername(username: String) {
        dataStore.edit { prefs ->
            prefs[USERNAME_KEY] = username
        }
    }

    suspend fun saveTotpSecret(totpSecret: String) {
        dataStore.edit { prefs ->
            prefs[TOTP_SECRET_KEY] = totpSecret
        }
    }

    fun getEndpointKey(): kotlinx.coroutines.flow.Flow<String?> {
        return dataStore.data.map { prefs -> prefs[ENDPOINT_KEY] }
    }

    fun getUserId(): kotlinx.coroutines.flow.Flow<String?> {
        return dataStore.data.map { prefs -> prefs[USER_ID_KEY] }
    }

    fun getUsername(): kotlinx.coroutines.flow.Flow<String?> {
        return dataStore.data.map { prefs -> prefs[USERNAME_KEY] }
    }

    fun getTotpSecret(): kotlinx.coroutines.flow.Flow<String?> {
        return dataStore.data.map { prefs -> prefs[TOTP_SECRET_KEY] }
    }
}