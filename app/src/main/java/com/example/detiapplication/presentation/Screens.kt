package com.example.detiapplication.presentation

sealed class Screens(val route: String) {
    object SelectionScreen: Screens(route = "selection_screen")

    object ParentSignInScreen: Screens(route = "parent_sign_in_screen")
    object ParentChangePasswordScreen: Screens(route = "parent_change_password_screen")
    object ParentInfoScreen: Screens(route = "parent_info_screen")
    object ParentQrScreen: Screens(route = "parent_qr_screen")
    object ParentRegistrationScreen: Screens(route = "parent_registration_screen")

    object ChildrenSignInScreen: Screens(route = "children_sign_in_screen")
    object ChilrenChangePasswordScreen: Screens(route = "children_change_password_screen")
    object ChidlrenRegistrationScreen: Screens(route = "children_registration_screen")
    object ChildrenInfoScreen: Screens(route = "children_info_screen")
    object ChildrenQrScreen: Screens(route = "chidlren_qr_screen")
}
