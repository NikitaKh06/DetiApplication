package com.example.detiapplication.presentation.screens

sealed class Screens(val route: String) {
    object SelectionScreen: Screens(route = "selection_screen")

    object ChildrenMainNavScreen: Screens(route = "main_nav_screen")
    object ParentMainNavScreen: Screens(route = "parent_main_nav_screen")

    object SearchChildrenScreen: Screens(route = "search_children_screen/{parent_email}")

    object ParentSignInScreen: Screens(route = "parent_sign_in_screen")
    object ParentInfoScreen: Screens(route = "parent_info_screen/{email}/{password}")
    object ParentRegistrationScreen: Screens(route = "parent_registration_screen")
    object ParentChangePasswordScreen: Screens(route = "parent_change_password_screen")

    object ChildrenSignInScreen: Screens(route = "children_sign_in_screen")
    object ChildrenChangePasswordScreen: Screens(route = "children_change_password_screen")
    object ChildrenRegistrationScreen: Screens(route = "children_registration_screen")
    object ChildrenInfoScreen: Screens(route = "children_info_screen/{parent_email}/{password}")
}
