package com.example.forgetpassword.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.forgetpassword.R
import com.example.forgetpassword.components.CustomButton
import com.example.forgetpassword.components.CustomTextField
import com.example.forgetpassword.data.DummyData
import com.example.forgetpassword.models.UserPreferences
import com.example.forgetpassword.screens.ui.theme.AtrDarkText
import com.example.forgetpassword.screens.ui.theme.AtrOrangePrimary
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    onLoginSuccess: (String) -> Unit,
    onForgetPasswordClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val userPreferences = remember { UserPreferences(context) }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 20.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.atr2),
            contentDescription = stringResource(id = R.string.login_img),
            modifier = Modifier.size(300.dp)
        )

        Spacer(modifier = Modifier.height(1.dp))

        Text(
            text = stringResource(R.string.login_title),
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = AtrDarkText
        )

        Spacer(modifier = Modifier.height(16.dp))

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

        Spacer(modifier = Modifier.height(12.dp))

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

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.forget_password),
            modifier = Modifier
                .align(Alignment.End)
                .clickable { onForgetPasswordClick() },
            color = AtrOrangePrimary,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.End
        )

        Spacer(modifier = Modifier.height(16.dp))

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
                        it.email == email && it.password == password
                    }
                    if (student != null) {
                        scope.launch {
                            userPreferences.saveUserSession(email)
                            onLoginSuccess(email)
                        }
                    } else {
                        emailError = "Wrong email or password"
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.dont_have_account),
                color = AtrDarkText.copy(alpha = 0.7f)
            )
            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = stringResource(id = R.string.sign_up),
                color = AtrOrangePrimary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { onSignUpClick() }
            )
        }
    }
}