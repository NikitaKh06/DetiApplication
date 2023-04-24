package com.example.detiapplication.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.detiapplication.presentation.theme.DetiApplicationTheme

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    //Сделать выбор начального экрана в зависимости от наличия токена
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        setContent {
            DetiApplicationTheme {
                navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}