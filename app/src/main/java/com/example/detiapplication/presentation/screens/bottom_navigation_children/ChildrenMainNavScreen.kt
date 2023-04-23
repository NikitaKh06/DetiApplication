package com.example.detiapplication.presentation.screens.bottom_navigation_children

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.detiapplication.presentation.theme.*

@Composable
fun ChildrenMainNavScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { ChildrenBottomBar(navController = navController) }
        ) {
            BottomNavGraphChildren(navController = navController, paddingValues = it)
    }
}

@Composable
fun ChildrenBottomBar(navController: NavController) {
    val screens = listOf(
        BottomBarScreensChildren.ProfileScreen,
        BottomBarScreensChildren.HomeScreen,
        BottomBarScreensChildren.SettingsScreen
    )

    BottomNavigation(
        backgroundColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination?.route
        screens.forEach{screen ->
            BottomNavigationItem(
                selected = currentDestination == screen.route,
                label = {
                    Text(text = screen.title)
                },
                icon = { Icon(imageVector = screen.icon, contentDescription = "Icon") },
                onClick = { navController.navigate(screen.route) },
                selectedContentColor = DarkGreen,
                unselectedContentColor = GreenSomeLight
            )
        }
    }
}