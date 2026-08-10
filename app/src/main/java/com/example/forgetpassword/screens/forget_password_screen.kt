package com.example.forgetpassword.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.forgetpassword.R
import com.example.forgetpassword.components.CustomButton
import com.example.forgetpassword.components.CustomTextField
import com.example.forgetpassword.data.DummyData
import com.example.forgetpassword.screens.ui.theme.AtrDarkText


@Composable
fun ForgetPasswordScreen(
    onOtpClick: (String) -> Unit,
    onBackToLoginClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        IconButton(
            onClick = { onBackToLoginClick() },
            modifier = Modifier.padding(start = 12.dp, top = 16.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(id = R.string.back),
                tint = AtrDarkText
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.forget_password),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = AtrDarkText
            )

            Spacer(modifier = Modifier.height(30.dp))

            CustomTextField(
                value = email,
                onValueChange = {
                    email = it
                    emailError = null
                },
                label = stringResource(R.string.email),
                modifier = Modifier.fillMaxWidth(),
                error = emailError,
                keyboardType = KeyboardType.Email
            )

            Spacer(modifier = Modifier.height(20.dp))

            CustomButton(
                text = stringResource(id = R.string.send_otp),
                onClick = {
                    emailError = null
                    if (email.isBlank()) {
                        emailError = "Email is required"
                    } else if (!email.contains("@")) {
                        emailError = "Invalid Email"
                    } else {
                        val exist = DummyData.students.any {
                            it.email == email
                        }
                        if (exist) {
                            onOtpClick(email)
                        } else {
                            emailError = "Email not found"
                        }
                    }
                }
            )
        }
    }
}