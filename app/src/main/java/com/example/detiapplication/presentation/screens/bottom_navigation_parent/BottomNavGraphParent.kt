package com.example.detiapplication.presentation.screens.bottom_navigation_parent

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun BottomNavGraphParent(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(navController = navController, startDestination = BottomBarScreensParent.ParentHomeScreen.route) {
        composable(
            route = BottomBarScreensParent.ParentProfileScreen.route
        ) {
            ParentProfileScreen()
        }

        composable(
            route = BottomBarScreensParent.ParentSettingsScreen.route
        ) {
            ParentSettingsScreen()
        }

        composable(
            route = BottomBarScreensParent.ParentHomeScreen.route
        ) {
            ParentHomeScreen()
        }
    }
}