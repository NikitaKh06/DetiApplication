package com.example.detiapplication.presentation.screens.bottom_navigation_children

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun BottomNavGraphChildren(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(navController = navController, startDestination = BottomBarScreensChildren.HomeScreen.route) {
        composable(
            route = BottomBarScreensChildren.ProfileScreen.route
        ) {
            ChildrenProfileScreen(navController = navController, paddingValues = paddingValues)
        }

        composable(
            route = BottomBarScreensChildren.SettingsScreen.route
        ) {
            ChildrenSettingsScreen()
        }

        composable(
            route = BottomBarScreensChildren.HomeScreen.route
        ) {
            ChildrenHomeScreen(navController = navController, bottomPaddingValues = paddingValues)
        }
    }
}