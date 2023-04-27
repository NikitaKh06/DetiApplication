package com.example.detiapplication.presentation.screens.bottom_navigation_children

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.detiapplication.presentation.screens.bottom_navigation_children.screens.ChildrenHomeScreen
import com.example.detiapplication.presentation.screens.bottom_navigation_children.screens.ChildrenHomeworkScreen
import com.example.detiapplication.presentation.screens.bottom_navigation_children.screens.ChildrenSettingsScreen

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

        composable(
            route = HomeScreens.ChildrenHomeworkScreen.route,
            arguments = listOf(
                navArgument(name = "subject_id") {
                    type = NavType.StringType
                } ,
                navArgument(name = "title") {
                    type = NavType.StringType
                }
            )
        ) {
            ChildrenHomeworkScreen(
                id =  it.arguments?.getString("subject_id").toString(),
                title =  it.arguments?.getString("title").toString()
            )
        }
    }
}