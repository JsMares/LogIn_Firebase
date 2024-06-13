package com.example.login_firebase.views.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.login_firebase.R
import com.example.login_firebase.navigation.Routes
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

@Composable
fun SplashScreen(navController: NavController) {

    val auth: FirebaseAuth = Firebase.auth

    LaunchedEffect(Unit) {
        if (!auth.currentUser?.email.isNullOrEmpty()) {
            navController.navigate(Routes.ScreenHome.route)
        }
        else {
            navController.navigate(Routes.ScreenLogin.route)
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.securepayment),
                contentDescription = "logo_app"
            )
            Text(
                text = "Loading...",
                modifier = Modifier.padding(top = 16.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )
        }
    }
}