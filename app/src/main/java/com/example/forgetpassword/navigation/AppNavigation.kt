package com.example.forgetpassword.navigation

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.forgetpassword.data.Routes
import com.example.forgetpassword.screens.*

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    var userEmailToReset by rememberSaveable { mutableStateOf("") }
    var loggedInEmail by rememberSaveable { mutableStateOf("") }

    NavHost(
        navController = navController,
        startDestination = Routes.splashNavigation
    ) {

        composable(Routes.splashNavigation) {
            SplashScreen(
                onTimeout = {
                    navController.navigate(Routes.loginNavigation) {
                        popUpTo(Routes.splashNavigation) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.loginNavigation) {
            LoginScreen(
                onLoginSuccess = { email ->
                    loggedInEmail = email
                    navController.navigate(Routes.homeNavigation) {
                        popUpTo(Routes.loginNavigation) { inclusive = true }
                    }
                },
                onForgetPasswordClick = {
                    navController.navigate(Routes.forgetPassNavigation)
                },
                onSignUpClick = {
                    navController.navigate(Routes.signupNavigation)
                }
            )
        }

        composable(Routes.signupNavigation) {
            SignUpScreen(
                onSignUpSuccess = {
                    navController.navigate(Routes.loginNavigation) {
                        popUpTo(Routes.signupNavigation) { inclusive = true }
                    }
                },
                onLoginClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.forgetPassNavigation) {
            ForgetPasswordScreen(
                onBackToLoginClick = {
                    navController.popBackStack()
                },
                onOtpClick = { email: String ->
                    userEmailToReset = email
                    navController.navigate(Routes.otpNavigation)
                }
            )
        }

        composable(Routes.otpNavigation) {
            OtpScreen(
                onOtpVerified = {
                    navController.navigate(Routes.newPassNavigation)
                }
            )
        }

        composable(Routes.newPassNavigation) {
            NewPasswordScreen(
                userEmail = userEmailToReset,
                onPasswordChangedSuccess = {
                    navController.navigate(Routes.loginNavigation) {
                        popUpTo(Routes.loginNavigation) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.homeNavigation) {
            HomeScreen(userEmail = loggedInEmail)
        }
    }
}