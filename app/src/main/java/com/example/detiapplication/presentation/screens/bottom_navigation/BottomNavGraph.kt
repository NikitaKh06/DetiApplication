package com.example.detiapplication.presentation.screens.bottom_navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun BottomNavGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(navController = navController, startDestination = BottomBarScreens.HomeScreen.route) {
        composable(
            route = BottomBarScreens.ProfileScreen.route
        ) {
            ProfileScreen()
        }

        composable(
            route = BottomBarScreens.SettingsScreen.route
        ) {
            SettingsScreen()
        }

        composable(
            route = BottomBarScreens.HomeScreen.route
        ) {
            HomeScreen(navController = navController, bottomPaddingValues = paddingValues)
        }
    }
}