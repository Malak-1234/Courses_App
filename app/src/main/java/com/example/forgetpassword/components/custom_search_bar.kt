package com.example.forgetpassword.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.forgetpassword.screens.ui.theme.AtrBackgroundGray
import com.example.forgetpassword.screens.ui.theme.AtrCardOutline
import com.example.forgetpassword.screens.ui.theme.AtrDarkText
import com.example.forgetpassword.screens.ui.theme.AtrOrangePrimary
import com.example.forgetpassword.screens.ui.theme.AtrSurfaceWhite

@Composable
fun CustomSearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = AtrOrangePrimary
            )
        },
        placeholder = {
            Text(
                text = "Search courses or topics...",
                color = AtrDarkText.copy(alpha = 0.5f)
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = AtrSurfaceWhite,
            unfocusedContainerColor = AtrBackgroundGray,
            focusedBorderColor = AtrOrangePrimary,
            unfocusedBorderColor = AtrCardOutline,
            focusedTextColor = AtrDarkText,
            unfocusedTextColor = AtrDarkText,
            cursorColor = AtrOrangePrimary
        )
    )
}