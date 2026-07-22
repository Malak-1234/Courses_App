package com.example.forgetpassword.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import com.example.forgetpassword.R
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
import com.example.forgetpassword.components.CustomButton
import com.example.forgetpassword.components.CustomTextField
import com.example.forgetpassword.data.DummyData
import com.example.forgetpassword.models.Student
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

@Composable
fun SignUpScreen(
    onSignUpSuccess: () -> Unit,
    onLoginClick: () -> Unit

) {

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var address by remember {mutableStateOf("")}
    var age by remember { mutableStateOf("")}
    var gpa by remember { mutableStateOf("")}
    var department by remember { mutableStateOf("") }


    var nameError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var phoneError by remember { mutableStateOf<String?>(null) }
    var addressError by remember {mutableStateOf(null)}
    var ageError by remember { mutableStateOf(null)}
    var gpaError by remember { mutableStateOf(null)}
    var departmentError by remember { mutableStateOf(null) }


    val scrollState = rememberScrollState()
    Column(

        modifier = Modifier
            .fillMaxSize().verticalScroll(scrollState)
            .padding(20.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = stringResource(id = R.string.sign_up_title),
            fontSize = 30.sp
        )

        Spacer(modifier = Modifier.height(30.dp))

        CustomTextField(

            value = name,

            onValueChange = {

                name = it
                nameError = null

            },
            label = stringResource(id = R.string.sign_up_name),
            modifier = Modifier.fillMaxWidth(),
            error = nameError
        )

        CustomTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = null
            },

            label = stringResource(id = R.string.email),
            modifier = Modifier.fillMaxWidth(),
            error = emailError,
            keyboardType = KeyboardType.Email
        )


        CustomTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = null
            },

            label = stringResource(id = R.string.password),
            modifier = Modifier.fillMaxWidth(),
            error = passwordError,
            isPassword = true
        )


        CustomTextField(
            value = phone,
            onValueChange = {
                phone = it
                phoneError = null
            },

            label = stringResource(id = R.string.sign_up_phone),
            modifier = Modifier.fillMaxWidth(),
            error = phoneError,
            keyboardType = KeyboardType.Number
        )

        CustomTextField(
            value = address,
            onValueChange = {
                address = it
                addressError = null
            },

            label = stringResource(id = R.string.sign_up_phone),
            modifier = Modifier.fillMaxWidth(),
            error = addressError,
            keyboardType = KeyboardType.Text
        )

        CustomTextField(
            value = age,
            onValueChange = {
                age = it
                ageError = null
            },

            label = stringResource(id = R.string.sign_up_phone),
            modifier = Modifier.fillMaxWidth(),
            error = ageError,
            keyboardType = KeyboardType.Number
        )


        CustomTextField(
            value = gpa,
            onValueChange = {
                gpa = it
                gpaError = null
            },

            label = stringResource(id = R.string.sign_up_phone),
            modifier = Modifier.fillMaxWidth(),
            error = gpaError,
            keyboardType = KeyboardType.Number
        )

        CustomTextField(
            value = department,
            onValueChange = {
                department = it
                departmentError = null
            },

            label = stringResource(id = R.string.sign_up_phone),
            modifier = Modifier.fillMaxWidth(),
            error = departmentError,
            keyboardType = KeyboardType.Text
        )

        Spacer(modifier = Modifier.height(25.dp))

        CustomButton(
            text = stringResource(id = R.string.sign_up_title),
            onClick = {
                nameError = null
                emailError = null
                passwordError = null
                phoneError = null

                if (name.isBlank()) {
                    nameError = "Name is required"
                }
                if (email.isBlank()) {
                    emailError = "Email is required"
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailError = "Invalid Email Format"
                }
                if (password.isBlank()) {
                    passwordError = "Password is required"
                }
                if (phone.length != 11) {
                    phoneError = "Phone must be 11 digits"
                }

                val exist = DummyData.students.any {
                    it.email == email
                }

                if (exist) {
                    emailError = "Email already exists"
                }
                if (
                    nameError == null && emailError == null && passwordError == null && phoneError == null
                ) {
                    val ageInt = age.toIntOrNull() ?: 0
                    val gpaDouble = gpa.toDoubleOrNull() ?: 0.0

                    DummyData.students.add(
                        Student(
                            name = name,
                            email = email,
                            password = password,
                            phone = phone,
                            address = address,
                            age = ageInt,
                            gpa = gpaDouble,
                            department = department
                        )
                    )
                    onSignUpSuccess()
                }
            }
        )

        Spacer(modifier = Modifier.height(5.dp))

        Row(
            modifier = Modifier.padding(top = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = stringResource(id = R.string.have_acc)
            )
            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = stringResource(id = R.string.log_in),
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {

                    onLoginClick()
                }
            )
            Spacer(modifier = Modifier.height(100.dp))

        }
    }
}