package com.example.forgetpassword.models

import android.location.Address

data class Student(

    val name: String,
    val email: String,
    var password: String,
    val phone: String,
    val address: String,
    val age: Int,
    val gpa: Double,
    val department: String
)