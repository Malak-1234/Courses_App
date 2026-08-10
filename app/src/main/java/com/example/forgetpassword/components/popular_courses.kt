package com.example.forgetpassword.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.forgetpassword.models.CourseItem

@Composable
fun PopularCoursesSection(
    courses: List<CourseItem>,
    onCourseClick: (CourseItem) -> Unit
) {
    Column {
        SectionHeader(title = "Popular Courses", onSeeAllClick = {})
        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 4.dp, vertical = 2.dp)
        ) {
            items(courses) { course ->
                CourseCard(
                    course = course,
                    onCourseClick = onCourseClick
                )
            }
        }
    }
}