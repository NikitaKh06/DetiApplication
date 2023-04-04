package com.example.detiapplication.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.SelectionScreen.route
    ) {
        composable(
            route = Screens.SelectionScreen.route
        ) {
            SelectionScreen()
        }
    }
}