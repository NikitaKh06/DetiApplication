package com.example.detiapplication.presentation.screens.bottom_navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreens(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object ProfileScreen: BottomBarScreens(
        route = "profile_screen",
        title = "Profile",
        icon = Icons.Default.Person
    )

    object HomeScreen: BottomBarScreens(
        route = "home_screen",
        title = "Home",
        icon = Icons.Default.Home
    )


    object SettingsScreen: BottomBarScreens(
        route = "settings_screen",
        title = "Settings",
        icon = Icons.Default.Settings
    )
}
