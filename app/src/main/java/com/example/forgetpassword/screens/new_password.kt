package com.example.forgetpassword.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.forgetpassword.components.CustomButton
import com.example.forgetpassword.components.CustomTextField
import com.example.forgetpassword.data.DummyData

@Composable
fun NewPasswordScreen(
    userEmail: String,
    onPasswordChangedSuccess: () -> Unit
) {
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var newPasswordError by remember { mutableStateOf<String?>(null) }
    var confirmPasswordError by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Reset Password",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(30.dp))

        CustomTextField(
            value = newPassword,
            onValueChange = {
                newPassword = it
                newPasswordError = null
            },
            label = "New Password",
            modifier = Modifier.fillMaxWidth(),
            error = newPasswordError,
            isPassword = true
        )

        Spacer(modifier = Modifier.height(15.dp))

        CustomTextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                confirmPasswordError = null
            },
            label = "Confirm New Password",
            modifier = Modifier.fillMaxWidth(),
            error = confirmPasswordError,
            isPassword = true
        )

        Spacer(modifier = Modifier.height(25.dp))

        CustomButton(
            text = "Confirm",
            onClick = {
                newPasswordError = null
                confirmPasswordError = null

                if (newPassword.isBlank()) {
                    newPasswordError = "Password cannot be empty"
                } else if (newPassword.length < 6) {
                    newPasswordError = "Password must be at least 6 characters"
                }

                if (confirmPassword != newPassword) {
                    confirmPasswordError = "Passwords do not match"
                }

                if (newPasswordError == null && confirmPasswordError == null) {
                    val student = DummyData.students.find { it.email == userEmail }
                    if (student != null) {
                        student.password = newPassword
                        onPasswordChangedSuccess()
                    } else {
                        newPasswordError = "User not found!"
                    }
                }
            }
        )
    }
}