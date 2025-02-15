package com.example.sh_2fa_app.navigation


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.sh_2fa_app.screens.Home
import com.example.sh_2fa_app.screens.Service
import com.example.sh_2fa_app.screens.Settings
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sh_2fa_app.data.viewmodels.PrefsViewModel
import com.example.sh_2fa_app.ui.AppColors


@Composable
fun MainScreen(viewModel: PrefsViewModel) {
    val navController = rememberNavController()

    Scaffold ( containerColor = AppColors.Background ,bottomBar = { BottomNavigationBar(navController = navController) }, modifier = Modifier.padding(0.dp)){
        padding ->
        AppNavHost(navController = navController, viewModel, padding)
    }

}

@Composable
fun AppNavHost(navController: NavHostController, prefsViewModel: PrefsViewModel, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = NavItem.Home.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(NavItem.Home.route) { Home(navController = navController, prefsViewModel) }
        composable(NavItem.Service.route) { Service(navController = navController, prefsViewModel) }
        composable(NavItem.Settings.route) { Settings(navController = navController, prefsViewModel) }
    }
}


@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(NavItem.Home, NavItem.Service, NavItem.Settings)

    NavigationBar(
        containerColor = AppColors.Accent
    ) {

        val currentBackStackEntry by navController.currentBackStackEntryAsState()

        val currentRoute = currentBackStackEntry?.destination?.route ?: NavItem.Home.route

        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(imageVector = item.icon, contentDescription = item.title, modifier = Modifier.size(30.dp),
                    )
                },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = AppColors.Primary ,
                    unselectedIconColor = AppColors.TextSecondary,
                    selectedTextColor = Color.White,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}