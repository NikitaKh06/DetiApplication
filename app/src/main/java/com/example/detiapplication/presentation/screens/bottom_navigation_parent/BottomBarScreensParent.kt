package com.example.detiapplication.presentation.screens.bottom_navigation_parent

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreensParent(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object ParentProfileScreen: BottomBarScreensParent(
        route = "profile_screen",
        title = "Profile",
        icon = Icons.Default.Person
    )

    object ParentHomeScreen: BottomBarScreensParent(
        route = "home_screen",
        title = "Home",
        icon = Icons.Default.Home
    )

    object ParentSettingsScreen: BottomBarScreensParent(
        route = "settings_screen",
        title = "Settings",
        icon = Icons.Default.Settings
    )
}