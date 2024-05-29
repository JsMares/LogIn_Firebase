package com.example.login_firebase.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.login_firebase.R

@Preview(showBackground = true)
@Composable
fun SignupScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        SignupHeader(modifier = Modifier.weight(1f))
        SignupBody(modifier = Modifier.weight(3f))
    }
}

@Composable
fun SignupHeader(modifier: Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Text(
            text = "Sign Up",
            color = Color.White,
            modifier = Modifier.align(Alignment.Center),
            fontSize = 46.sp
        )
    }
}

@Composable
fun SignupBody(modifier: Modifier) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(topStart = 80.dp))
            .fillMaxSize()
            .background(Color.White)
    ) {
        FormOutlinedTextField(
            label = stringResource(id = R.string.nameUser),
            icon = Icons.Default.Person,
            contentDescription = "PersonIcon",
            modifier = Modifier.padding(top = 80.dp)
        )
        FormOutlinedTextField(
            label = stringResource(id = R.string.lastNameUser),
            icon = Icons.Default.Person,
            contentDescription = "PersonIcon",
            modifier = Modifier.padding(top = 20.dp)
        )
        FormOutlinedTextField(
            label = stringResource(id = R.string.email),
            icon = Icons.Default.Email,
            contentDescription = "EmailIcon",
            modifier = Modifier.padding(top = 20.dp)
        )
        FormOutlinedTextField(
            label = stringResource(id = R.string.password),
            icon = Icons.Default.Lock,
            contentDescription = "LockIcon",
            modifier = Modifier.padding(top = 20.dp)
        )
        Box(modifier = Modifier.fillMaxSize()) {
            SignupButton(modifier = Modifier.align(Alignment.Center))
            SignupIndicationText(modifier = Modifier.align(Alignment.BottomCenter))
        }
    }
}

@Composable
fun FormOutlinedTextField(
    label: String,
    icon: ImageVector,
    contentDescription: String,
    modifier: Modifier
) {
    OutlinedTextField(
        value = "",
        onValueChange = { },
        label = { Text(text = label) },
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 16.dp)
    )
}

@Composable
fun SignupButton(modifier: Modifier) {
    Button(
        onClick = { },
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(Color.Black)
        )
    {
        Text(text = stringResource(id = R.string.register))
    }
}

@Composable
fun SignupIndicationText(modifier: Modifier) {
    Row(modifier = modifier.padding(bottom = 20.dp)) {
        Text(text = stringResource(id = R.string.IndicationLoginQuestion), modifier = Modifier.align(Alignment.CenterVertically))
        TextButton(onClick = { }) {
            Text(text = stringResource(id = R.string.logIn), fontWeight = FontWeight.Bold, color = Color.Black)
        }
    }
}