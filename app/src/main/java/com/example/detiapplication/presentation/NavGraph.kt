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
            ChildrenSignInScreen(navController = navController)
        }
        composable(
            route = Screens.ChildrenRegistrationScreen.route
        ) {
            ChidlrenRegistrationScreen(navController = navController)
        }
        composable(
            route = Screens.ChildrenInfoScreen.route
        ) {
            ChildrenInfoScreen(navController = navController)
        }
        composable(
            route = Screens.ChildrenQrScreen.route
        ) {
            ChildrenQrScreen()
        }


        //Parent
        composable(
            route = Screens.ParentSignInScreen.route
        ) {
            ParentSignInScreen(navController = navController)
        }
        composable(
            route = Screens.ParentRegistrationScreen.route
        ) {
            ParentRegistrationScreen(navController = navController)
        }
        composable(
            route = Screens.ParentInfoScreen.route
        ) {
            ParentInfoScreen(navController = navController)
        }
        composable(
            route = Screens.ParentQrScreen.route
        ) {
            ParentQrScreen()
        }
    }
}