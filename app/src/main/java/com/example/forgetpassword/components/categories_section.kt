package com.example.forgetpassword.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.forgetpassword.R
import com.example.forgetpassword.models.CategoryItem
import com.example.forgetpassword.screens.ui.theme.AtrCategoryBg
import com.example.forgetpassword.screens.ui.theme.AtrDarkText
import com.example.forgetpassword.screens.ui.theme.AtrOrangePrimary

@Composable
fun CategoriesSection(
    onSeeAllClick: () -> Unit = {}
){
    val categories = listOf(
        CategoryItem(stringResource(id = R.string.programming), icon = R.drawable.ic_programming, iconTint = AtrOrangePrimary),
        CategoryItem(stringResource(id = R.string.ui_ux), icon = R.drawable.ic_ui, iconTint = AtrOrangePrimary),
        CategoryItem(stringResource(id = R.string.web_dev), icon = R.drawable.ic_web, iconTint = AtrOrangePrimary),
        CategoryItem(stringResource(id = R.string.mobile_dev), icon = R.drawable.ic_mobile, iconTint = AtrOrangePrimary),
        CategoryItem(stringResource(id = R.string.ai), icon = R.drawable.ic_ai, iconTint = AtrOrangePrimary),
    )

    Column {
        SectionHeader(title = "Categories", onSeeAllClick = onSeeAllClick)
        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(categories) { category ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(70.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(54.dp)
                            .clip(CircleShape)
                            .background(AtrCategoryBg),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = category.icon),
                            contentDescription = category.name,
                            modifier = Modifier.size(24.dp),
                            colorFilter = ColorFilter.tint(category.iconTint)
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = category.name,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = AtrDarkText,
                        maxLines = 1
                    )
                }
            }
        }
    }
}