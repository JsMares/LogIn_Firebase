package com.example.login_firebase.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.login_firebase.viewModel.HomeViewModel
import com.example.login_firebase.viewModel.LoginViewModel
import com.example.login_firebase.views.home.HomeScreen
import com.example.login_firebase.views.login.LoginScreen
import com.example.login_firebase.views.login.SignupScreen
import com.example.login_firebase.views.login.SplashScreen

@Composable
fun NavManager(loginViewModel: LoginViewModel, homeViewModel: HomeViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.ScreenSplash.route) {
        composable(Routes.ScreenSplash.route) { SplashScreen(navController = navController) }
        composable(Routes.ScreenLogin.route) {
            LoginScreen(
                navController = navController,
                loginViewModel = loginViewModel
            )
        }
        composable(Routes.ScreenSignup.route) {
            SignupScreen(
                navController = navController,
                loginViewModel = loginViewModel
            )
        }
        composable(Routes.ScreenHome.route) { backStackEntry ->
            HomeScreen(
                navController = navController,
                homeViewModel = homeViewModel,
                idUser = backStackEntry.arguments?.getString("idUser").orEmpty()
            )
        }
    }
}