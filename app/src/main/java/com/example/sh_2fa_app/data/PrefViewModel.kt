package com.example.sh_2fa_app.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PrefsViewModel(private val userPrefs: UserPrefs) : ViewModel() {
    val username: StateFlow<String?> = userPrefs.getUsername()
        .stateIn(viewModelScope, SharingStarted.Eagerly, null);

    val userId: StateFlow<String?> = userPrefs.getUserId()
        .stateIn(viewModelScope, SharingStarted.Eagerly, null)

    val totpSecret: StateFlow<String?> = userPrefs.getTotpSecret()
        .stateIn(viewModelScope, SharingStarted.Eagerly, null)

    val endpoint: StateFlow<String?> = userPrefs.getEndpointKey()
        .stateIn(viewModelScope, SharingStarted.Eagerly, null)

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