package com.example.login_firebase.navigation

sealed class Routes(val route: String) {
    data object ScreenLogin: Routes("screenLogin")
    data object ScreenSignup: Routes("screenSignup")
    data object ScreenHome: Routes("screenHome")
}