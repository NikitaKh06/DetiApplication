package com.example.detiapplication.presentation.screens.bottom_navigation

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.detiapplication.presentation.theme.*

@Composable
fun MainNavScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
        ) {
            BottomNavGraph(navController = navController, paddingValues = it)
        }
}

@Composable
fun BottomBar(navController: NavController) {
    val screens = listOf(
        BottomBarScreens.ProfileScreen,
        BottomBarScreens.HomeScreen,
        BottomBarScreens.SettingsScreen
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