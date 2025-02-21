package com.example.sh_2fa_app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavItem(val route: String, val icon: ImageVector, val title: String) {
    object Home : NavItem("home", Icons.Filled.Home, "Home")
    object Service : NavItem("service", Icons.Filled.Build, "Service")
    object Settings : NavItem("settings", Icons.Filled.Settings, "Settings")
    object BindRequests: NavItem("bindRequests", Icons.Filled.Add, "Bind requests")
}
