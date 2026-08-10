package com.example.forgetpassword.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.forgetpassword.R
import com.example.forgetpassword.components.SettingsItem
import com.example.forgetpassword.components.SettingsSwitchItem
import com.example.forgetpassword.screens.ui.theme.AtrSurfaceWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onBackClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onChangePasswordClick: () -> Unit = {}
) {
    var isDarkMode by remember { mutableStateOf(false) }
    var isNotificationsEnabled by remember { mutableStateOf(true) }

    var showLanguageDialog by remember { mutableStateOf(false) }
    var selectedLanguage by remember { mutableStateOf("English") }

    Scaffold(
        containerColor = AtrSurfaceWhite,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AtrSurfaceWhite,
                    titleContentColor = AtrDarkText,
                    navigationIconContentColor = AtrDarkText
                ),
                title = {
                    Text(
                        text = stringResource(id = R.string.settings),
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.back)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.account),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = AtrOrangePrimary
            )

            SettingsItem(
                icon = Icons.Default.Person,
                title = stringResource(id = R.string.edit_profile),
                onClick = onProfileClick
            )

            SettingsItem(
                icon = Icons.Default.Security,
                title = stringResource(id = R.string.change_password),
                onClick = onChangePasswordClick
            )

            HorizontalDivider(color = AtrCardOutline, modifier = Modifier.padding(vertical = 8.dp))

            Text(
                text = stringResource(id = R.string.preferences),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = AtrOrangePrimary
            )

            SettingsSwitchItem(
                icon = Icons.Default.Notifications,
                title = stringResource(id = R.string.notifications),
                checked = isNotificationsEnabled,
                onCheckedChange = { isNotificationsEnabled = it }
            )

            SettingsSwitchItem(
                icon = if (isDarkMode) Icons.Default.DarkMode else Icons.Default.LightMode,
                title = if (isDarkMode) "Dark Mode" else "Light Mode",
                checked = isDarkMode,
                onCheckedChange = { isDarkMode = it }
            )

            SettingsItem(
                icon = Icons.Default.Language,
                title = stringResource(id = R.string.language),
                subtitle = selectedLanguage,
                onClick = { showLanguageDialog = true }
            )
        }
    }

    if (showLanguageDialog) {
        AlertDialog(
            onDismissRequest = { showLanguageDialog = false },
            containerColor = AtrSurfaceWhite,
            title = {
                Text(
                    text = stringResource(id = R.string.language),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = AtrDarkText
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                selectedLanguage = "English"
                                showLanguageDialog = false
                            }
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedLanguage == "English",
                            onClick = {
                                selectedLanguage = "English"
                                showLanguageDialog = false
                            },
                            colors = RadioButtonDefaults.colors(selectedColor = AtrOrangePrimary)
                        )
                        Text(
                            text = "English",
                            fontSize = 16.sp,
                            color = AtrDarkText,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }

                    HorizontalDivider(color = AtrCardOutline)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                selectedLanguage = "العربية"
                                showLanguageDialog = false
                            }
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedLanguage == "العربية",
                            onClick = {
                                selectedLanguage = "العربية"
                                showLanguageDialog = false
                            },
                            colors = RadioButtonDefaults.colors(selectedColor = AtrOrangePrimary)
                        )
                        Text(
                            text = "العربية",
                            fontSize = 16.sp,
                            color = AtrDarkText,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            },
            confirmButton = {},
            dismissButton = {
                TextButton(onClick = { showLanguageDialog = false }) {
                    Text(text = "Cancel", color = AtrOrangePrimary)
                }
            }
        )
    }
}

////////////setting_malak