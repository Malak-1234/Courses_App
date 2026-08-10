package com.example.forgetpassword.navigation

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.forgetpassword.data.DummyData
import com.example.forgetpassword.data.Routes
import com.example.forgetpassword.models.UserPreferences
import com.example.forgetpassword.screens.*
import kotlinx.coroutines.launch

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val userPreferences = remember { UserPreferences(context) }
    val scope = rememberCoroutineScope()

    val isLoggedIn by userPreferences.isLoggedIn.collectAsState(initial = false)
    val savedEmail by userPreferences.userEmail.collectAsState(initial = "")
    var userEmailToReset by remember { mutableStateOf("") }

    NavHost(
        navController = navController,
        startDestination = Routes.splashNavigation
    ) {
        composable(Routes.splashNavigation) {
            SplashScreen(
                onTimeout = {
                    val target = if (isLoggedIn) Routes.homeNavigation else Routes.loginNavigation
                    navController.navigate(target) {
                        popUpTo(Routes.splashNavigation) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.loginNavigation) {
            LoginScreen(
                onLoginSuccess = { email ->
                    navController.navigate(Routes.homeNavigation) {
                        popUpTo(Routes.loginNavigation) { inclusive = true }
                    }
                },
                onForgetPasswordClick = { navController.navigate(Routes.forgetPassNavigation) },
                onSignUpClick = { navController.navigate(Routes.signupNavigation) }
            )
        }

        composable(Routes.signupNavigation) {
            SignUpScreen(
                onSignUpSuccess = {
                    navController.navigate(Routes.loginNavigation) {
                        popUpTo(Routes.signupNavigation) { inclusive = true }
                    }
                },
                onLoginClick = { navController.popBackStack() }
            )
        }

        composable(Routes.forgetPassNavigation) {
            ForgetPasswordScreen(
                onBackToLoginClick = { navController.popBackStack() },
                onOtpClick = { email: String ->
                    userEmailToReset = email
                    navController.navigate(Routes.otpNavigation)
                }
            )
        }

        composable(Routes.otpNavigation) {
            OtpScreen(
                onOtpVerified = { navController.navigate(Routes.newPassNavigation) }
            )
        }

        composable(Routes.newPassNavigation) {
            NewPasswordScreen(
                userEmail = userEmailToReset,
                onPasswordChangedSuccess = {
                    navController.navigate(Routes.loginNavigation) {
                        popUpTo(Routes.newPassNavigation) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.homeNavigation) {
            StudentHomeScreen(
                userEmail = savedEmail,
                navController = navController,
                onProfileClick = { navController.navigate(Routes.profileNavigation) },
                onJopOfferClick = { navController.navigate(Routes.jobOffers) },
                onSettingsClick = { navController.navigate(Routes.settings) },
                onLogoutClick = {
                    scope.launch {
                        userPreferences.clearUserSession()
                        navController.navigate(Routes.loginNavigation) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                }
            )
        }

        composable(Routes.settings) {
            SettingsScreen(
                onBackClick = { navController.popBackStack() },
                onProfileClick = { navController.navigate(Routes.profileNavigation) },
                onChangePasswordClick = { navController.navigate(Routes.forgetPassNavigation) }
            )
        }

        composable(Routes.jobOffers) {
            JobOffersScreen(
                onBackClick = { navController.popBackStack() },
                onApplyClick = { navController.navigate(Routes.uploadCv) }
            )
        }

        composable(Routes.uploadCv) {
            UploadCvScreen(
                onBackClick = { navController.popBackStack() },
                onUploadClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.profileNavigation) {
            ProfileScreen(
                userEmail = savedEmail,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(Routes.attendance) {
            AttendanceScreen(
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(Routes.categories) {
            CategoriesScreen(
                onBackClick = { navController.popBackStack() },
                onCategoryClick = { },
                onCourseClick = { course ->
                    val route = "course_details/${course.id}"
                    if (navController.currentDestination?.route != route) {
                        navController.navigate(route) {
                            launchSingleTop = true
                        }
                    }
                }
            )
        }

        composable(
            route = "course_details/{courseId}",
            arguments = listOf(navArgument("courseId") { type = NavType.IntType })
        ) { backStackEntry ->
            val courseId = backStackEntry.arguments?.getInt("courseId") ?: 1
            val selectedCourse = DummyData.courses.find { it.id == courseId } ?: DummyData.courses.first()
            CourseDetailsScreen(
                course = selectedCourse,
                onBackClick = { navController.popBackStack() },
                onStartCourseClick = { }
            )
        }
    }
}