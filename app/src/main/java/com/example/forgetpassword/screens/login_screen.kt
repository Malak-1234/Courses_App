package com.example.forgetpassword.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.forgetpassword.R
import com.example.forgetpassword.components.CustomButton
import com.example.forgetpassword.components.CustomTextField
import com.example.forgetpassword.data.DummyData

@Composable
fun LoginScreen(

    onLoginSuccess: (String) -> Unit,
    onForgetPasswordClick: () -> Unit,
    onSignUpClick: () -> Unit

) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {

        Text(
            text = stringResource(R.string.login_title),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
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

        Spacer(modifier = Modifier.height(15.dp))

        CustomTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = null
            },

            label = stringResource(R.string.password),
            modifier = Modifier.fillMaxWidth(),
            error = passwordError,
            isPassword = true
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = stringResource(R.string.forget_password),
            modifier = Modifier
                .align(Alignment.End)
                .clickable {
                    onForgetPasswordClick()
                },

            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(25.dp))

        CustomButton(
            text = stringResource(R.string.login_title),
            onClick = {
                emailError = null
                passwordError = null
                if (email.isBlank()) {
                    emailError = "Email is required"
                } else if (!email.contains("@")) {
                    emailError = "Invalid Email"
                }

                if (password.isBlank()) {
                    passwordError = "Password is required"
                }

                if (emailError == null && passwordError == null) {
                    val student = DummyData.students.find {
                        it.email == email &&
                                it.password == password
                    }
                    if (student != null) {
                        onLoginSuccess(email)
                    } else {
                        emailError = "Wrong email or password"
                    }
                }
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Text(
                text = "Don't have an account?"
            )
            Spacer(modifier = Modifier.width(5.dp))

            Text(
                text = "Sign Up",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    onSignUpClick()
                }
            )
        }
    }
}