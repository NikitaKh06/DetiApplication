package com.example.detiapplication.presentation.screens.bottom_navigation_parent

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.detiapplication.presentation.screens.bottom_navigation_children.ChildrenBottomBar

@Composable
fun ParentMainNavScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { ChildrenBottomBar(navController = navController) }
    ) {
        BottomNavGraphParent(navController = navController, paddingValues = it)
    }
}