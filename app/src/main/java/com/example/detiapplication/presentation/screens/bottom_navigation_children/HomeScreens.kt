package com.example.detiapplication.presentation.screens.bottom_navigation_children

sealed class HomeScreens(
    val route: String
) {
    object ChildrenHomeworkScreen: HomeScreens(route = "children_homework_screen/{subject_id}/{title}")
}
