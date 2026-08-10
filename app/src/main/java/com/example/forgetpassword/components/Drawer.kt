package com.example.forgetpassword.components

import com.example.forgetpassword.R
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.forgetpassword.screens.ui.theme.AtrDarkText
import com.example.forgetpassword.screens.ui.theme.AtrOrangePrimary
import com.example.forgetpassword.screens.ui.theme.AtrSurfaceWhite


@Composable
fun DrawerContent(
    onProfileClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onAttendanceClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onJopOfferClick: () -> Unit
) {
    ModalDrawerSheet(
        drawerContainerColor = AtrSurfaceWhite
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(id = R.string.student_menu),
            style = MaterialTheme.typography.titleLarge,
            color = AtrDarkText,
            modifier = Modifier.padding(16.dp)
        )

        HorizontalDivider(modifier = Modifier.padding(bottom = 12.dp))

        NavigationDrawerItem(
            label = { Text(stringResource(id = R.string.profile), color = AtrDarkText) },
            icon = { Icon(Icons.Default.Person, contentDescription = null, tint = AtrOrangePrimary) },
            selected = false,
            onClick = onProfileClick,
            modifier = Modifier.padding(horizontal = 12.dp)
        )

        NavigationDrawerItem(
            label = { Text(stringResource(id = R.string.attendance), color = AtrDarkText) },
            icon = { Icon(Icons.Default.CalendarMonth, contentDescription = null, tint = AtrOrangePrimary) },
            selected = false,
            onClick = onAttendanceClick,
            modifier = Modifier.padding(horizontal = 12.dp)
        )

        NavigationDrawerItem(
            label = { Text(stringResource(id = R.string.settings), color = AtrDarkText) },
            icon = { Icon(Icons.Default.Settings, contentDescription = null, tint = AtrOrangePrimary) },
            selected = false,
            onClick = onSettingsClick,
            modifier = Modifier.padding(horizontal = 12.dp)
        )

        NavigationDrawerItem(
            label = { Text(stringResource(id = R.string.jop_offer), color = AtrDarkText) },
            icon = { Icon(Icons.Default.Work, contentDescription = null, tint = AtrOrangePrimary) },
            selected = false,
            onClick = onJopOfferClick,
            modifier = Modifier.padding(horizontal = 12.dp)
        )

        NavigationDrawerItem(
            label = { Text(stringResource(id = R.string.logout), color = MaterialTheme.colorScheme.error) },
            icon = { Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = null, tint = MaterialTheme.colorScheme.error) },
            selected = false,
            onClick = onLogoutClick,
            modifier = Modifier.padding(horizontal = 12.dp)
        )
    }
}