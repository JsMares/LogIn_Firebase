package com.example.login_firebase.views.login

import android.widget.Toast
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
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.login_firebase.R
import com.example.login_firebase.StoreUserData
import com.example.login_firebase.navigation.Routes
import com.example.login_firebase.viewModel.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun LoginScreen(navController: NavController, loginViewModel: LoginViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        HeaderLogin(modifier = Modifier.weight(1f))
        BodyLogin(modifier = Modifier.weight(2f), navController, loginViewModel)
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
fun BodyLogin(modifier: Modifier, navController: NavController, loginViewModel: LoginViewModel) {
    val showPassword by loginViewModel.showPassword.observeAsState(false)

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(modifier = modifier.fillMaxSize()) {
        HeaderLoginText(Modifier.align(Alignment.CenterHorizontally))
        EmailTextField(email) { email = it }
        PasswordTextField(password, showPassword) { password = it }
        ShowPasswordCheckBox(loginViewModel)
        Box(modifier = modifier.fillMaxSize()) {
            LogInButton(
                modifier = Modifier.align(Alignment.Center),
                navController = navController,
                loginViewModel = loginViewModel,
                email = email,
                password = password
            )
            RegisterIndicationText(
                modifier = Modifier.align(Alignment.BottomCenter),
                navController = navController
            )
        }
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
fun EmailTextField(email: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(value = email, onValueChange = { onValueChange(it) }, modifier = Modifier
        .fillMaxWidth()
        .padding(start = 12.dp, end = 12.dp, top = 25.dp),
        label = { Text(text = stringResource(id = R.string.email)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Email icon") }
    )
}

@Composable
fun PasswordTextField(password: String, show: Boolean, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = password,
        onValueChange = { onValueChange(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp, top = 20.dp),
        label = { Text(text = stringResource(id = R.string.password)) },
        visualTransformation = if (!show) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "Lock icon") }
    )
}

@Composable
fun ShowPasswordCheckBox(loginViewModel: LoginViewModel) {
    //val checkedState = remember { mutableStateOf(false) }
    val checkedState by loginViewModel.showPassword.observeAsState(false)

    Row {
        Checkbox(
            checked = checkedState,
            onCheckedChange = { if (it) loginViewModel.onShowPassword() else loginViewModel.onHidePassword() },
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Black,
                uncheckedColor = Color.Black,
                checkmarkColor = Color.White
            )
        )
        Text(
            text = stringResource(id = R.string.showPassword),
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun LogInButton(
    modifier: Modifier,
    navController: NavController,
    loginViewModel: LoginViewModel,
    email: String,
    password: String
) {
    val context = LocalContext.current
    val dataStore = StoreUserData(context = context)
    val message = stringResource(id = R.string.errorEmailPassword)

    Button(
        onClick = {
            loginViewModel.login(email, password) { response ->
                if (response != "ERROR" && response != "DOCUMENT_NOT_FOUND"){
                    CoroutineScope(Dispatchers.IO).launch {
                        dataStore.saveUser(idUser = response)
                        withContext(Dispatchers.Main) {
                            navController.navigate(Routes.ScreenHome.createRoute(response))
                        }
                    }
                }
                else
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }, modifier = modifier
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


