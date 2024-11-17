package com.example.sh_2fa_app.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sh_2fa_app.ui.AppColors

@Composable
fun Base(
    title: String = "Default title",
    content: @Composable (PaddingValues) -> Unit) {

    Scaffold(containerColor = AppColors.Background, modifier = Modifier.padding(20.dp)) { paddingValues ->
        content(paddingValues)
    }
}