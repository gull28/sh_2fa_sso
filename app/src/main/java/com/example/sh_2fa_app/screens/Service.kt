package com.example.sh_2fa_app.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.example.sh_2fa_app.data.PrefsViewModel
import com.example.sh_2fa_app.ui.components.Base
import com.example.sh_2fa_app.ui.components.ServiceAdapter

@Composable
fun Service(navController: NavController, prefsViewModel: PrefsViewModel) {
    LaunchedEffect(Unit) {
        prefsViewModel.fetchServices()
    }

    Base(title = "Service page") {
        ServiceAdapter(prefsViewModel)
    }
}