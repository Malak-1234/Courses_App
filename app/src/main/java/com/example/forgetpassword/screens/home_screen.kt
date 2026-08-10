package com.example.forgetpassword.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.forgetpassword.R
import com.example.forgetpassword.components.CategoriesSection
import com.example.forgetpassword.components.CustomSearchBar
import com.example.forgetpassword.components.DrawerContent
import com.example.forgetpassword.components.PopularCoursesSection
import com.example.forgetpassword.components.SpecialOffer
import com.example.forgetpassword.data.DummyData.courses
import com.example.forgetpassword.data.Routes
import com.example.forgetpassword.screens.ui.theme.AtrBackground
import com.example.forgetpassword.screens.ui.theme.AtrOrange
import com.example.forgetpassword.screens.ui.theme.AtrDark
import kotlinx.coroutines.launch

@Composable
fun StudentHomeScreen(
    userEmail: String,
    onProfileClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onJopOfferClick: () -> Unit,
    navController: NavController
) {
    var searchText by remember { mutableStateOf("") }

    val filteredCourses = courses.let { list ->
        list.filter { course ->
            course.title.contains(searchText, ignoreCase = true)
        }
    }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    var showLogoutDialog by remember { mutableStateOf(false) }

    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            title = {
                Text(
                    text = stringResource(id = R.string.logout_warning),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            },
            text = {
                Text(text = stringResource(id = R.string.logout_question))
            },
            confirmButton = {
                TextButton(
                    onClick = { showLogoutDialog = false }
                ) {
                    Text(text = stringResource(id = R.string.no), color = Color.Gray)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showLogoutDialog = false
                        onLogoutClick()
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.yes),
                        color = AtrOrange,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        )
    }

    val extractedName = userEmail.run {
        if (contains("@")) substringBefore("@") else this
    }.let { raw ->
        raw.replaceFirstChar { if (it.isLowerCase()) it.uppercase() else it.toString() }
    }

    fun closeDrawerAnd(action: () -> Unit) {
        scope.launch {
            drawerState.close()
        }.also {
            action()
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .fillMaxWidth(0.80f)
                    .fillMaxHeight(),
                drawerContainerColor = AtrBackground
            ) {
                DrawerContent(
                    onAttendanceClick = {
                        closeDrawerAnd { navController.navigate(Routes.attendance) }
                    },
                    onProfileClick = {
                        closeDrawerAnd(onProfileClick)
                    },
                    onSettingsClick = {
                        closeDrawerAnd(onSettingsClick)
                    },
                    onJopOfferClick = {
                        closeDrawerAnd(onJopOfferClick)
                    },
                    onLogoutClick = {
                        closeDrawerAnd { showLogoutDialog = true }
                    }
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AtrBackground)
                .statusBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(45.dp)
                        .clip(CircleShape)
                        .background(AtrOrange)
                        .clickable {
                            scope.launch { drawerState.open() }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = extractedName.takeIf { it.isNotEmpty() }?.take(1)?.uppercase() ?: "U",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = stringResource(id = R.string.welcome_back),
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = extractedName,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = AtrDark
                    )
                }
            }

            CustomSearchBar(
                value = searchText,
                onValueChange = {
                    searchText = it
                }
            )

            SpecialOffer()

            CategoriesSection(
                onSeeAllClick = {
                    navController.navigate(Routes.categories)
                }
            )

            if (filteredCourses.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.no_results),
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                }
            } else {
                PopularCoursesSection(
                    courses = filteredCourses,
                    onCourseClick = { course ->
                        navController.navigate(
                            Routes.getCourseDetailsRoute(course.id)
                        )
                    }
                )
            }
        }
    }
}