package com.example.detiapplication.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.detiapplication.data.repositories.parent_repositories.ParentRegistrationRepository
import com.example.detiapplication.presentation.theme.DetiApplicationTheme

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    private lateinit var viewModel: MainViewModel
    private lateinit var parentRepository: ParentRegistrationRepository
//Сделать выбор начального экрана в зависимости от наличия токена
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        viewModel = ViewModelProvider(this, MainViewModelFactory(this))[MainViewModel::class.java]
        setContent {
            DetiApplicationTheme {
                parentRepository = ParentRegistrationRepository(context = this)
                navController = rememberNavController()
                SetupNavGraph(navController = navController, viewModel = viewModel)
            }
        }
    }
}