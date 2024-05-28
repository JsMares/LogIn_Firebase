package com.example.login_firebase.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun LoginScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        HeaderLogin(modifier = Modifier.weight(1f))
        BodyLogin(modifier = Modifier.weight(2f))
    }
}

@Composable
fun HeaderLogin(modifier: Modifier) {
    Box(
        modifier = modifier
            .background(Color.Black)
            .fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "",
            tint = Color.White,
            modifier = Modifier
                .align(Alignment.Center)
                .size(150.dp)
        )
    }
}

@Composable
fun BodyLogin(modifier: Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        HeaderLoginText(Modifier.align(Alignment.CenterHorizontally))
        EmailTextField()
    }
}

@Composable
fun HeaderLoginText(modifier: Modifier) {
    Text(
        text = "Log In",
        fontWeight = FontWeight.Bold,
        fontSize = 45.sp,
        modifier = modifier.padding(top = 25.dp)
    )
}

@Composable
fun EmailTextField() {
    OutlinedTextField(value = "", onValueChange = { }, modifier = Modifier
        .fillMaxWidth()
        .padding(start = 12.dp, end = 12.dp, top = 25.dp),
        label = { Text(text = "Correo Electronico") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Email icon") }
    )
}