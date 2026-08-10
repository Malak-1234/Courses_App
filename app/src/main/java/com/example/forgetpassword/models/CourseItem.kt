package com.example.forgetpassword.models

data class CourseItem(
    val id: Int,
    val title: String,
    val category: String,
    val instructor: String,
    val rating: Double,
    val studentsCount: Int,
    val isEnrolled: Boolean,
    val imageRes: Int,
    val originalPrice: Double,
    val discountedPrice: Double,
    val discountPercentage: Int,
    val totalHours: Int,
    val description: String,
    val sections: List<CourseSection>,
    var isFavorite: Boolean = false
)
