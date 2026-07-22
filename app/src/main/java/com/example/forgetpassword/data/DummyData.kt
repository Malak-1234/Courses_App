package com.example.forgetpassword.data

import com.example.forgetpassword.models.Student
object DummyData {

    const val VALID_OTP = "123456"
    val students = mutableListOf(

        Student(
            name = "Malak",
            email = "malak@gmail.com",
            password = "123456",
            phone = "01012345678",
            address = "19 middle School Street - Warraq",
            age = 17,
            gpa = 99.0,
            department = "Computer Science"
        ),

        Student(
            name = "Radwa",
            email = "radwa@gmail.com",
            password = "31243124",
            phone = "01212345678",
            address = "19 middle School Street - Warraq",
            age = 26,
            gpa = 99.0,
            department = "Civil Engineering"
        ),

        Student(
            name = "Eng_John",
            email = "john@gmail.com",
            password = "000000",
            phone = "0112345678",
            address = "none",
            age = 40,
            gpa = 99.0,
            department = "Android App"
        )

    )

}