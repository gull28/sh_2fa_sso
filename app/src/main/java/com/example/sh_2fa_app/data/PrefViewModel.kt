package com.example.sh_2fa_app.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sh_2fa_app.data.api.ApiService
import com.example.sh_2fa_app.data.api.CreateUserRequest
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PrefsViewModel(private val userPrefs: UserPrefs, private val apiService: ApiService) : ViewModel() {
    val username: StateFlow<String?> = userPrefs.getUsername()
        .stateIn(viewModelScope, SharingStarted.Eagerly, null);

    val userId: StateFlow<String?> = userPrefs.getUserId()
        .stateIn(viewModelScope, SharingStarted.Eagerly, null)

    val totpSecret: StateFlow<String?> = userPrefs.getTotpSecret()
        .stateIn(viewModelScope, SharingStarted.Eagerly, null)

    val endpoint: StateFlow<String?> = userPrefs.getEndpointKey()
        .stateIn(viewModelScope, SharingStarted.Eagerly, null)

    fun generateUser(username: String, endpoint: String) {
        viewModelScope.launch {
            try {
                val response = apiService.createUser("", CreateUserRequest(username))

                if (response.isSuccessful) {
                    response.body()?.let { user ->
                        saveUsername(user.username)
                        saveUserId(user.id)
                        saveTotpSecret(user.totp_secret)
                        saveEndpoint("")
                    } ?: run {
                        println("Empty body")
                    }
                } else {
                    println("Error: ${response.code()} ${response.message()}")
                }
            }catch (e: Exception) {
                println("Error ${e.message}")
            }
        }
    }

    fun saveUsername(username: String) {
        viewModelScope.launch {
            userPrefs.saveUsername(username)
        }
    }

    fun saveUserId(userId: String) {
        viewModelScope.launch {
            userPrefs.saveUserId(userId)
        }
    }

    fun saveTotpSecret(secret: String) {
        viewModelScope.launch {
            userPrefs.saveTotpSecret(secret)
        }
    }

    fun saveEndpoint(endpoint: String) {
        viewModelScope.launch {
            userPrefs.saveEndpointKey(endpoint)
        }
    }
}