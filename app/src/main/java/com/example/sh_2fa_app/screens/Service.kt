package com.example.sh_2fa_app.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.example.sh_2fa_app.data.viewmodels.PrefsViewModel
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