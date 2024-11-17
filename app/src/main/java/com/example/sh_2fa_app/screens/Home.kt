package com.example.sh_2fa_app.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sh_2fa_app.data.PrefsViewModel
import com.example.sh_2fa_app.ui.AppColors
import com.example.sh_2fa_app.ui.components.Base

@Composable
fun Home(navController: NavController, prefsViewModel: PrefsViewModel) {
    Base(title = "Home page") {
        Row(modifier = Modifier.fillMaxSize(),verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            Text("hello world", color = AppColors.TextPrimary, fontSize = 24.sp)
        }
    }
}