package com.example.login_firebase.views.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.login_firebase.R
import com.example.login_firebase.navigation.Routes

@Composable
fun LoginScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        HeaderLogin(modifier = Modifier.weight(1f))
        BodyLogin(modifier = Modifier.weight(2f))
        TailLogin(modifier = Modifier.weight(1f), navController = navController)
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
        PasswordTextField()
        ShowPasswordCheckBox()
    }
}

@Composable
fun TailLogin(modifier: Modifier, navController: NavController) {
    Box(modifier = modifier.fillMaxSize()) {
        LogInButton(modifier = Modifier.align(Alignment.Center), navController)
        RegisterIndicationText(modifier = Modifier.align(Alignment.BottomCenter), navController = navController)
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
        label = { Text(text = stringResource(id = R.string.email)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Email icon") }
    )
}

@Composable
fun PasswordTextField() {
    OutlinedTextField(
        value = "",
        onValueChange = { },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp, top = 20.dp),
        label = { Text(text = stringResource(id = R.string.password)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "Lock icon") }
    )
}

@Composable
fun ShowPasswordCheckBox() {
    val checkedState = remember { mutableStateOf(false) }

    Row {
        Checkbox(checked = checkedState.value, onCheckedChange = { checkedState.value = it })
        Text(
            text = stringResource(id = R.string.showPassword),
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun LogInButton(modifier: Modifier, navController: NavController) {
    Button(
        onClick = { navController.navigate(Routes.ScreenHome.route) }, modifier = modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(Color.Black)
    ) {
        Text(text = stringResource(id = R.string.logInButton))
    }
}

@Composable
fun RegisterIndicationText(modifier: Modifier, navController: NavController) {
    Row(
        modifier = modifier.padding(bottom = 20.dp)
    ) {
        Text(
            text = stringResource(id = R.string.IndicationRegisterQuestion),
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        TextButton(onClick = { navController.navigate(Routes.ScreenSignup.route) }) {
            Text(
                text = stringResource(id = R.string.register),
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


