package com.example.forgetpassword.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.example.forgetpassword.R

@Composable
fun HomeScreen(userEmail: String) {
    val userName = userEmail.substringBefore("@")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Welcome, $userName",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.my_custom_color)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = stringResource(id = R.string.special_offers), fontWeight = FontWeight.Bold)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.discount),
                modifier = Modifier.padding(16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = stringResource(id = R.string.prog_lang), fontWeight = FontWeight.Bold)
        Text(
            text = "Kotlin   Python   Java   C++",
            modifier = Modifier.padding(vertical = 8.dp),
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = stringResource(id = R.string.courses), fontWeight = FontWeight.Bold)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Android Development with Jetpack Compose", fontWeight = FontWeight.Medium)
                Text(text = "Level: Beginner", color = Color.Gray, fontSize = 12.sp)
            }
        }
    }
}