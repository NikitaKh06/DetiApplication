package com.example.detiapplication.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.detiapplication.presentation.screens.*
import com.example.detiapplication.presentation.screens.bottom_navigation_children.*
import com.example.detiapplication.presentation.screens.bottom_navigation_parent.ParentMainNavScreen

@Composable
fun SetupNavGraph(navController: NavHostController, logInStatus: Boolean, logInType: String) {

    NavHost(
        navController = navController,
        startDestination =
        if(!logInStatus) Screens.SelectionScreen.route
        else
            if(logInType == "Children") Screens.ChildrenMainNavScreen.route
            else Screens.ParentMainNavScreen.route
    ) {

        composable(
            route = Screens.ParentMainNavScreen.route
        ) {
            ParentMainNavScreen()
        }

        composable(
            route = Screens.ChildrenMainNavScreen.route
        ) {
            ChildrenMainNavScreen()
        }

        composable(
            route = Screens.SearchChildrenScreen.route,
            arguments = listOf(
                navArgument(name = "parent_email") {
                    type = NavType.StringType
                }
            )
        ) {
            SearchChildrenScreen(
                navController = navController,
                parentEmail = it.arguments?.getString("parent_email").toString()
            )
        }

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
                parentEmail = it.arguments?.getString("parent_email").toString(),
                password = it.arguments?.getString("password").toString()
            )
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
                email = it.arguments?.getString("email").toString(),
                password = it.arguments?.getString("password").toString()
            )
        }
    }
}