package com.example.detiapplication.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.detiapplication.data.repositories.children_repositories.ChildrenRegistrationRepository
import com.example.detiapplication.presentation.theme.DetiApplicationTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    val childrenRegistrationRepository: ChildrenRegistrationRepository by inject()
    override fun onCreate(savedInstanceState: Bundle?, ) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        val logInStatus = childrenRegistrationRepository.checkLogInStatus().isLogIn
        val type = childrenRegistrationRepository.checkLogInStatus().type

        setContent {
            DetiApplicationTheme {
                navController = rememberNavController()
                SetupNavGraph(navController = navController, logInStatus = logInStatus, logInType = type)
            }
        }
    }
}