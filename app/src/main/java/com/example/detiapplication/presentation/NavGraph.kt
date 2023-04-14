package com.example.detiapplication.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
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
            route = Screens.ChildrenInfoScreen.route,
            arguments = listOf(
                navArgument(name = "parent_email") {
                    type = NavType.StringType
                },
                navArgument(name = "password") {
                    type = NavType.StringType
                }
            )
        ) {
            ChildrenInfoScreen(
                navController = navController,
                viewModel = viewModel,
                parentEmail = it.arguments?.getString("parent_email").toString(),
                password = it.arguments?.getString("password").toString()
            )
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
            route = Screens.ParentInfoScreen.route,
            arguments = listOf(
                navArgument(name = "email") {
                    type = NavType.StringType
                },
                navArgument(name = "password") {
                    type = NavType.StringType
                }
            )
        ) {
            ParentInfoScreen(
                navController = navController,
                viewModel = viewModel,
                email = it.arguments?.getString("email").toString(),
                password = it.arguments?.getString("password").toString()
            )
        }

        composable(
            route = Screens.ParentQrScreen.route
        ) {
            ParentQrScreen(navController = navController, viewModel = viewModel)
        }
    }
}