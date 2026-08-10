package com.example.forgetpassword.screens

import com.example.forgetpassword.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.forgetpassword.components.CustomButton
import com.example.forgetpassword.data.DummyData

val AtrOrangePrimary = Color(0xFFEA8E2C)
val AtrDarkText = Color(0xFF2B2D42)
val AtrCardOutline = Color(0xFFE5E7EB)

@Composable
fun OtpScreen(
    onOtpVerified: () -> Unit
) {
    val otpValues = remember { mutableStateListOf("", "", "", "", "", "") }
    val focusRequesters = remember { List(6) { FocusRequester() } }
    var otpError by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.enter_otp_code),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = AtrDarkText
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = stringResource(id = R.string.we_sent_you),
            color = AtrDarkText.copy(alpha = 0.6f),
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (i in 0 until 6) {
                OutlinedTextField(
                    value = otpValues[i],
                    onValueChange = { value ->
                        val canWrite = (0 until i).all { otpValues[it].isNotEmpty() }

                        if (canWrite) {
                            val digitsOnly = value.filter { it.isDigit() }

                            if (digitsOnly.length <= 1) {
                                otpValues[i] = digitsOnly
                                otpError = null

                                if (digitsOnly.isNotEmpty() && i < 5) {
                                    focusRequesters[i + 1].requestFocus()
                                }
                            }
                        } else {
                            val firstEmptyIndex = otpValues.indexOfFirst { it.isEmpty() }
                            if (firstEmptyIndex != -1) {
                                focusRequesters[firstEmptyIndex].requestFocus()
                            }
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                        .focusRequester(focusRequesters[i])
                        .onKeyEvent { keyEvent ->
                            if (keyEvent.type == KeyEventType.KeyUp && keyEvent.key == Key.Backspace) {
                                if (otpValues[i].isEmpty() && i > 0) {
                                    otpValues[i - 1] = ""
                                    focusRequesters[i - 1].requestFocus()
                                    true
                                } else {
                                    false
                                }
                            } else {
                                false
                            }
                        },
                    textStyle = TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = AtrDarkText
                    ),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = AtrOrangePrimary,
                        unfocusedBorderColor = AtrCardOutline,
                        cursorColor = AtrOrangePrimary
                    ),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        }

        if (otpError != null) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = otpError!!, color = Color(0xFFE53935))
        }

        Spacer(modifier = Modifier.height(30.dp))

        CustomButton(
            text = stringResource(id = R.string.verify_otp),
            onClick = {
                val enteredOtp = otpValues.joinToString("")
                if (enteredOtp.length < 6) {
                    otpError = "Please enter full 6-digit code"
                } else if (enteredOtp == DummyData.VALID_OTP) {
                    onOtpVerified()
                } else {
                    otpError = "Invalid OTP code (Try: 123456)"
                }
            }
        )
    }
}