package com.example.detiapplication.presentation.screens.bottom_navigation_children

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreensChildren(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object ProfileScreen: BottomBarScreensChildren(
        route = "profile_screen",
        title = "Profile",
        icon = Icons.Default.Person
    )

    object HomeScreen: BottomBarScreensChildren(
        route = "home_screen",
        title = "Home",
        icon = Icons.Default.Home
    )


    object SettingsScreen: BottomBarScreensChildren(
        route = "settings_screen",
        title = "Settings",
        icon = Icons.Default.Settings
    )
}
