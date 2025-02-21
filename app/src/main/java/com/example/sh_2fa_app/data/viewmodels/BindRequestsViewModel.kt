package com.example.sh_2fa_app.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sh_2fa_app.data.models.ServiceItem

class BindRequestsViewModel()  : ViewModel(){

    private val _bindRequests = MutableLiveData<List<ServiceItem>>(mutableListOf())
    val bindRequests: LiveData<List<ServiceItem>> get() = _bindRequests
}