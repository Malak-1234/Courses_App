package com.example.forgetpassword.data

import com.example.forgetpassword.R
import com.example.forgetpassword.models.CourseItem
import com.example.forgetpassword.models.CourseSection
import com.example.forgetpassword.models.SpecialOfferItem
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

    val dummyOffers = listOf(
        SpecialOfferItem(
            id = 1,
            categoryTag = "Special Offer",
            title = "50% OFF",
            subtitle = "on Android Development",
            discountTag = "50%\nOFF",
            imageRes = R.drawable.senior,
            buttonText = "Explore Now"
        ),
        SpecialOfferItem(
            id = 2,
            categoryTag = "Limited Deal",
            title = "30% OFF",
            subtitle = "on UI/UX Design Masterclass",
            discountTag = "30%\nOFF",
            imageRes = R.drawable.ui_ux,
            buttonText = "Get Started"
        ),
        SpecialOfferItem(
            id = 3,
            categoryTag = "Hot Discount",
            title = "Free Trial",
            subtitle = "on Kotlin Basics for Beginners",
            discountTag = "FREE",
            imageRes = R.drawable.kotlin,
            buttonText = "Enroll Free"
        )
    )

    val courses = listOf(
        CourseItem(
            id = 1,
            title = "Android Development with Kotlin",
            category = "Mobile Dev",
            instructor = "John Esmat",
            rating = 4.8,
            studentsCount = 1200,
            isEnrolled = true,
            imageRes = R.drawable.android_icon,
            originalPrice = 99.99,
            discountedPrice = 94.99,
            discountPercentage = 50,
            totalHours = 18,
            description = "Learn Android Development from scratch using Kotlin and Jetpack Compose. You'll build real-world projects and understand modern Android architecture.",
            sections = listOf(
                CourseSection("Section 1: Introduction", 3),
                CourseSection("Section 2: Android Basics", 8),
                CourseSection("Section 3: Jetpack Compose", 10),
                CourseSection("Section 4: Firebase & APIs", 6)
            )
        ),
        CourseItem(
            id = 2,
            title = "Kotlin Basics for Beginners",
            category = "Programming",
            instructor = "Jane Smith",
            rating = 4.7,
            studentsCount = 900,
            isEnrolled = false,
            imageRes = R.drawable.kotlin,
            originalPrice = 99.99,
            discountedPrice = 94.99,
            discountPercentage = 50,
            totalHours = 18,
            description = "Master Kotlin programming language fundamentals from total zero to building complete logic algorithms.",
            sections = listOf(
                CourseSection("Section 1: Introduction", 3),
                CourseSection("Section 2: Kotlin Basics", 8),
                CourseSection("Section 3: Jetpack Compose", 10),
                CourseSection("Section 4: Firebase & APIs", 6)
            )
        ),
        CourseItem(
            id = 3,
            title = "Flutter tutorial advanced level",
            category = "Mobile Dev",
            instructor = "Malak Sameh",
            rating = 4.7,
            studentsCount = 900,
            isEnrolled = true,
            imageRes = R.drawable.flutter,
            originalPrice = 99.99,
            discountedPrice = 94.99,
            discountPercentage = 50,
            totalHours = 18,
            description = "Deep dive into cross-platform state management, custom animations, and clean architecture with Flutter",
            sections = listOf(
                CourseSection("Section 1: Introduction", 3),
                CourseSection("Section 2: Flutter Basics", 8),
                CourseSection("Section 3: Dart Compose", 10),
                CourseSection("Section 4: Firebase & APIs", 6)
            )
        ),
        CourseItem(
            id = 4,
            title = "Python Masterclass (Very Important)",
            category = "Programming",
            instructor = "Jane Smith",
            rating = 4.7,
            studentsCount = 900,
            isEnrolled = false,
            imageRes = R.drawable.python,
            originalPrice = 99.99,
            discountedPrice = 94.99,
            discountPercentage = 50,
            totalHours = 18,
            description = "Learn Python programming from scratch, covering variables, functions, object-oriented programming, and real-world projects. Build a strong foundation for web development, automation, data analysis, and more.",
            sections = listOf(
                CourseSection("Section 1: Introduction", 3),
                CourseSection("Section 2: Python Basics", 8),
                CourseSection("Section 3: Jetpack Compose", 10),
                CourseSection("Section 4: Firebase & APIs", 6)
            )
        ),
        CourseItem(
            id = 5,
            title = "Artificial Intelligence tutorial",
            category = "AI",
            instructor = "Jane Smith",
            rating = 4.7,
            studentsCount = 900,
            isEnrolled = false,
            imageRes = R.drawable.ai,
            originalPrice = 99.99,
            discountedPrice = 94.99,
            discountPercentage = 50,
            totalHours = 18,
            description = "Explore the fundamentals of Artificial Intelligence, including machine learning, neural networks, computer vision, and natural language processing. Learn how AI powers modern applications through practical examples.",
            sections = listOf(
                CourseSection("Section 1: Introduction", 3),
                CourseSection("Section 2: AI Basics", 8),
                CourseSection("Section 3: Jetpack Compose", 10),
                CourseSection("Section 4: Firebase & APIs", 6)
            )
        )
    )
}