package com.example.detiapplication.presentation.screens.bottom_navigation_parent

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.detiapplication.presentation.screens.HomeScreens
import com.example.detiapplication.presentation.screens.bottom_navigation_parent.screens.ChildrenProfileFromParentScreen
import com.example.detiapplication.presentation.screens.bottom_navigation_parent.screens.ParenHomeworkScreen
import com.example.detiapplication.presentation.screens.bottom_navigation_parent.screens.ParentHomeScreen
import com.example.detiapplication.presentation.screens.bottom_navigation_parent.screens.ParentProfileScreen

@Composable
fun BottomNavGraphParent(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(navController = navController, startDestination = BottomBarScreensParent.ParentHomeScreen.route) {
        composable(
            route = BottomBarScreensParent.ParentProfileScreen.route
        ) {
            ParentProfileScreen(paddingValues = paddingValues)
        }

        composable(
            route = BottomBarScreensParent.ParentSettingsScreen.route
        ) {
            ParentSettingsScreen()
        }

        composable(
            route = BottomBarScreensParent.ParentHomeScreen.route
        ) {
            ParentHomeScreen(navController = navController, bottomPaddingValues = paddingValues)
        }

        composable(
            route = HomeScreens.ChildrenProfileFromParent.route
        ) {
            ChildrenProfileFromParentScreen()
        }

        composable(
            route = HomeScreens.ParentHomeworkScreen.route,
            arguments = listOf(
                navArgument(name = "subject_id") {
                    type = NavType.StringType
                } ,
                navArgument(name = "title") {
                    type = NavType.StringType
                }
            )
        ) {
            ParenHomeworkScreen(
                id =  it.arguments?.getString("subject_id").toString(),
                title =  it.arguments?.getString("title").toString(),
                paddingValues = paddingValues
            )
        }
    }
}