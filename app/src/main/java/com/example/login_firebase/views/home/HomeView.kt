package com.example.login_firebase.views.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.login_firebase.R
import com.example.login_firebase.viewModel.HomeViewModel

@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel, idUser: String) {
    Column(modifier = Modifier.fillMaxSize()) {
        HeaderHome()
        Spacer(modifier = Modifier.height(20.dp))
        DetailsText(
            indicator = stringResource(id = R.string.IndicatorName),
            value = idUser
        )
        DetailsText(
            indicator = stringResource(id = R.string.IndicatorEmail),
            value = "jsmares0105@gmail.com"
        )
        DetailsText(
            indicator = stringResource(id = R.string.IndicatorDate),
            value = "04/06/2024"
        )
    }
    Box(modifier = Modifier.fillMaxSize()) {
        LogoutButton(
            Modifier.align(Alignment.BottomCenter),
            navController = navController,
            homeViewModel = homeViewModel
        )
    }
}

@Composable
fun HeaderHome() {
    Text(
        text = stringResource(id = R.string.HeaderHomeText),
        fontSize = 40.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(12.dp)
    )
}

@Composable
fun DetailsText(indicator: String, value: String) {
    Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp)) {
        Text(text = indicator, fontSize = 18.sp, fontWeight = FontWeight.Black)
        Text(text = value, fontSize = 18.sp)
    }
}

@Composable
fun LogoutButton(modifier: Modifier, navController: NavController, homeViewModel: HomeViewModel) {
    Button(
        onClick = {
            homeViewModel.signOut()
            navController.popBackStack()
        },
        modifier = modifier
            .padding(12.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(Color.Black)
    ) {
        Text(text = stringResource(id = R.string.logoutButton))
    }
}