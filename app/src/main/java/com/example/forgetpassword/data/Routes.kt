package com.example.forgetpassword.data

object Routes {
    const val homeNavigation = "home"
    const val splashNavigation = "splash"
    const val forgetPassNavigation = "forgetPass"
    const val loginNavigation = "login"
    const val signupNavigation = "signup"
    const val newPassNavigation = "newPass"
    const val otpNavigation = "otp"
    const val profileNavigation = "profile"


    const val courseDetailsNavigation = "course_details/{courseId}"
    fun getCourseDetailsRoute(courseId: Int) = "course_details/$courseId"

    const val attendance = "attendance"
    const val categories = "categories"

    const val uploadCv = "upload_cv"
    const val jobOffers = "job_offers"
    const val settings = "settings"
}