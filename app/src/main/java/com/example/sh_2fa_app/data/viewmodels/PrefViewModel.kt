package com.example.sh_2fa_app.data.viewmodels

import android.util.JsonReader
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sh_2fa_app.data.UserPrefs
import com.example.sh_2fa_app.data.api.ApiService
import com.example.sh_2fa_app.data.api.CreateUserRequest
import com.example.sh_2fa_app.data.models.BindRequest
import com.example.sh_2fa_app.data.models.ServiceItem
import com.example.sh_2fa_app.data.models.UnboundServiceItem
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

    private val _services = MutableLiveData<List<ServiceItem>>(mutableListOf())
    val services: LiveData<List<ServiceItem>> get() = _services

    private val _bindRequests = MutableLiveData<List<BindRequest>>(mutableListOf())
    val bindRequests: LiveData<List<BindRequest>> get() = _bindRequests

    private val _unboundServices = MutableLiveData<List<UnboundServiceItem>>(mutableListOf())
    val unboundServices: LiveData<List<UnboundServiceItem>> get() = _unboundServices

    fun generateUser(username: String, endpoint: String) {
        viewModelScope.launch {
            try {
                val response = apiService.createUser("http://10.0.2.2:8080/users", CreateUserRequest(username))

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

    fun fetchBindRequests(): List<BindRequest>? {
        viewModelScope.launch {
            try {
                val resp = apiService.fetchBindRequests("http://10.0.2.2:8080/bind/${userId.value.toString()}")

                if(resp.isSuccessful) {
                    resp.body()?.let { fetchedBindRequests ->
                        println("Respbody: ${fetchedBindRequests}")
                        _bindRequests.value = fetchedBindRequests.bindRequests
                    } ?: run {
                        println("Empty body")
                    }
                }else {
                    println("error")
                }
            }catch (e: Exception) {
                println("Error: ${e.message}")
            }
        }
        return _bindRequests.value
    }

    fun unbindService(id: UInt) {
        viewModelScope.launch {
            try {
                val resp = apiService.unbindServiceLink("http://10.0.2.2:8080/service/unlink/${id}")

                println("Response123123: ${resp.toString()}");

                if (resp.isSuccessful) {
                    resp.body()?.let { fetchedServices ->
                        println("Respbody: ${fetchedServices}")
                        _services.value = fetchedServices.services
                        _unboundServices.value = fetchedServices.nonBoundServices
                    } ?: run {
                        println("Empty body")
                    }
                } else {
                    println("Error: ${resp.code()} ${resp.message()}")
                }
            }catch (e: Exception) {
                println("Error: ${e.message}")
            }
        }
    }

    fun fetchServices(): List<ServiceItem>? {
        viewModelScope.launch {
            try {
                // todo: change to pref variable in prod
                val resp = apiService.fetchServices("http://10.0.2.2:8080/service/${userId.value.toString()}")

                println("Response123123: ${resp.toString()}");

                if (resp.isSuccessful) {
                    resp.body()?.let { fetchedServices ->
                        println("Respbody: ${fetchedServices}")
                        _services.value = fetchedServices.services
                        _unboundServices.value = fetchedServices.nonBoundServices
                    } ?: run {
                        println("Empty body")
                    }
                } else {
                    println("Error: ${resp.code()} ${resp.message()}")
                }
            }catch (e: Exception) {
                println("Error: ${e.message}")
            }
        }

        return _services.value;

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