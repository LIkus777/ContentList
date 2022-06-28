package com.template.screens.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.template.screens.finish.FinishScreen
import com.template.screens.main.MainScreen
import com.template.screens.main.MainViewModel
import com.template.screens.splash.SplashScreen

@Composable
fun ApplicationScreen() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavigationTree.Splash.name) {
        composable(route = NavigationTree.Splash.name) {
            SplashScreen(navController = navController)
        }
        composable(route = NavigationTree.Main.name) {
            val mainViewModel = hiltViewModel<MainViewModel>()
            MainScreen(navController = navController, mainViewModel = mainViewModel)
        }
        composable(route = NavigationTree.Finish.name) {
            FinishScreen(navController = navController)
        }
    }
}