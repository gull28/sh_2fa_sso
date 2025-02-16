package com.example.sh_2fa_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.sh_2fa_app.data.viewmodels.PrefsViewModel
import com.example.sh_2fa_app.data.UserPrefsViewModelFactory
import com.example.sh_2fa_app.navigation.MainScreen

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: PrefsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = UserPrefsViewModelFactory(this)
        viewModel = ViewModelProvider(this, factory)[PrefsViewModel::class.java]

        setContent {
            MainScreen(viewModel)
        }
    }
}
