package com.example.detiapplication.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.detiapplication.presentation.screens.Screens

@Composable
fun SetupNavGraph(navController: NavHostController, viewModel: MainViewModel) {

    NavHost(
        navController = navController,
        startDestination = Screens.SelectionScreen.route
    ) {

        //Welcome
        composable(
            route = Screens.SelectionScreen.route
        ) {
            SelectionScreen(navController = navController)
        }

        //Children
        composable(
            route = Screens.ChildrenSignInScreen.route
        ) {
            ChildrenSignInScreen(navController = navController, viewModel = viewModel)
        }

        composable(
            route = Screens.ChildrenRegistrationScreen.route
        ) {
            ChidlrenRegistrationScreen(navController = navController, viewModel = viewModel)
        }

        composable(
            route = Screens.ChildrenInfoScreen.route
        ) {
            ChildrenInfoScreen(navController = navController, viewModel = viewModel)
        }

        composable(
            route = Screens.ChildrenQrScreen.route
        ) {
            ChildrenQrScreen(navController = navController, viewModel = viewModel)
        }

        //Parent
        composable(
            route = Screens.ParentSignInScreen.route
        ) {
            ParentSignInScreen(navController = navController, viewModel = viewModel)
        }

        composable(
            route = Screens.ParentRegistrationScreen.route
        ) {
            ParentRegistrationScreen(navController = navController, viewModel = viewModel)
        }

        composable(
            route = Screens.ParentInfoScreen.route
        ) {
            ParentInfoScreen(navController = navController, viewModel = viewModel)
        }

        composable(
            route = Screens.ParentQrScreen.route
        ) {
            ParentQrScreen(navController = navController, viewModel = viewModel)
        }
    }
}