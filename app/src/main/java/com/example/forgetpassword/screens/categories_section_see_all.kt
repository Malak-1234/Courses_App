package com.example.forgetpassword.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.forgetpassword.R
import com.example.forgetpassword.components.CourseCard
import com.example.forgetpassword.components.CustomSearchBar
import com.example.forgetpassword.data.DummyData
import com.example.forgetpassword.models.CategoryItem
import com.example.forgetpassword.models.CourseItem
import com.example.forgetpassword.screens.ui.theme.AtrBackgroundGray
import com.example.forgetpassword.screens.ui.theme.AtrSurfaceWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesScreen(
    onBackClick: () -> Unit,
    onCategoryClick: (CategoryItem) -> Unit = {},
    onCourseClick: (CourseItem) -> Unit = {}
) {
    var searchText by remember { mutableStateOf("") }

    val categoriesList = remember {
        listOf(
            CategoryItem("Programming", icon = R.drawable.ic_programming, iconTint = Color(0xFF4C6EF5)),
            CategoryItem("UI/UX", icon = R.drawable.ic_ui, iconTint = Color(0xFFE599F7)),
            CategoryItem("Web Dev", icon = R.drawable.ic_web, iconTint = Color(0xFF38D9A9)),
            CategoryItem("Mobile Dev", icon = R.drawable.ic_mobile, iconTint = Color(0xFFFF8787)),
            CategoryItem("AI", icon = R.drawable.ic_ai, iconTint = Color(0xFFFFD43B))
        )
    }

    var selectedCategory by remember { mutableStateOf(categoriesList.firstOrNull()) }

    val filteredCategories = categoriesList.filter {
        it.name.contains(searchText, ignoreCase = true)
    }

    val filteredCourses = remember(selectedCategory, searchText) {
        DummyData.courses.filter { course ->
            (selectedCategory == null || course.category == selectedCategory?.name) &&
                    course.title.contains(searchText, ignoreCase = true)
        }
    }

    Scaffold(
        containerColor = AtrSurfaceWhite,
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = AtrSurfaceWhite,
                    titleContentColor = AtrDarkText,
                    navigationIconContentColor = AtrDarkText
                ),
                title = {
                    Text(
                        text = stringResource(R.string.all_categories),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CustomSearchBar(
                value = searchText,
                onValueChange = { searchText = it }
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(
                    items = filteredCategories,
                    key = { category -> category.name }
                ) { category ->
                    val isSelected = category == selectedCategory

                    Card(
                        modifier = Modifier
                            .height(56.dp)
                            .clickable {
                                selectedCategory = category
                                onCategoryClick(category)
                            },
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isSelected) AtrOrangePrimary else AtrBackgroundGray
                        ),
                        border = if (isSelected) null else androidx.compose.foundation.BorderStroke(1.dp, AtrCardOutline),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(horizontal = 12.dp, vertical = 8.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(if (isSelected) Color.White.copy(alpha = 0.2f) else AtrSurfaceWhite),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = category.icon),
                                    contentDescription = category.name,
                                    modifier = Modifier.size(18.dp),
                                    colorFilter = ColorFilter.tint(if (isSelected) Color.White else category.iconTint)
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = category.name,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = if (isSelected) Color.White else AtrDarkText
                            )
                        }
                    }
                }
            }

            selectedCategory?.let { category ->
                Text(
                    text = "${category.name} Courses",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = AtrDarkText
                )
            }

            if (filteredCourses.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.MenuBook,
                            contentDescription = null,
                            modifier = Modifier.size(64.dp),
                            tint = AtrDarkText.copy(alpha = 0.3f)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = stringResource(id = R.string.no_available_courses),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = AtrDarkText.copy(alpha = 0.6f)
                        )
                    }
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    items(
                        items = filteredCourses,
                        key = { course -> course.id }
                    ) { course ->
                        CourseCard(
                            course = course,
                            onCourseClick = { onCourseClick(course) }
                        )
                    }
                }
            }
        }
    }
}