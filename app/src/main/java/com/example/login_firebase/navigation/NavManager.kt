package com.example.login_firebase.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.login_firebase.views.home.HomeScreen
import com.example.login_firebase.views.login.LoginScreen
import com.example.login_firebase.views.login.SignupScreen

@Composable
fun NavManager() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.ScreenLogin.route) {
        composable(Routes.ScreenLogin.route) { LoginScreen(navController) }
        composable(Routes.ScreenSignup.route) { SignupScreen() }
        composable(Routes.ScreenHome.route) { HomeScreen() }
    }
}