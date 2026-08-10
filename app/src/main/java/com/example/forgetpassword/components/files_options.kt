package com.example.forgetpassword.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.forgetpassword.R
import com.example.forgetpassword.screens.ui.theme.AtrBackgroundGray
import com.example.forgetpassword.screens.ui.theme.AtrCardOutline
import com.example.forgetpassword.screens.ui.theme.AtrDarkText


@Composable
fun FilesOption() {
    val chipColors = SuggestionChipDefaults.suggestionChipColors(
        containerColor = AtrBackgroundGray,
        labelColor = AtrDarkText
    )
    val chipBorder = BorderStroke(1.dp, AtrCardOutline)

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(id = R.string.accepted_formats),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = AtrDarkText
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            SuggestionChip(
                onClick = { },
                label = { Text(stringResource(id = R.string.pdf)) },
                colors = chipColors,
                border = chipBorder
            )

            SuggestionChip(
                onClick = { },
                label = { Text(stringResource(id = R.string.doc)) },
                colors = chipColors,
                border = chipBorder
            )

            SuggestionChip(
                onClick = { },
                label = { Text(stringResource(id = R.string.docx)) },
                colors = chipColors,
                border = chipBorder
            )
        }
    }
}