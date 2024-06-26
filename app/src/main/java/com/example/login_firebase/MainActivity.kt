package com.example.login_firebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.login_firebase.navigation.NavManager
import com.example.login_firebase.ui.theme.LogIn_FirebaseTheme
import com.example.login_firebase.viewModel.HomeViewModel
import com.example.login_firebase.viewModel.LoginViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val loginViewModel: LoginViewModel by viewModels()
        val homeViewModel: HomeViewModel by viewModels()

        setContent {
            LogIn_FirebaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavManager(loginViewModel = loginViewModel, homeViewModel = homeViewModel)
                }
            }
        }
    }
}
