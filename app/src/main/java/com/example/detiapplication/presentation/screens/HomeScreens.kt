package com.example.detiapplication.presentation.screens

sealed class HomeScreens(
    val route: String
) {
    object ChildrenHomeworkScreen: HomeScreens(route = "children_homework_screen/{subject_id}/{title}")
    object ChildrenProfileFromParent: HomeScreens(route = "children_profile_from_parent")
}
