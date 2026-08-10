package com.example.forgetpassword.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.forgetpassword.R
import com.example.forgetpassword.models.JobOffers
import com.example.forgetpassword.screens.ui.theme.AtrBackgroundGray
import com.example.forgetpassword.screens.ui.theme.AtrCardOutline
import com.example.forgetpassword.screens.ui.theme.AtrDarkText
import com.example.forgetpassword.screens.ui.theme.AtrOrangePrimary
import com.example.forgetpassword.screens.ui.theme.AtrSurfaceWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobOffersScreen(
    onBackClick: () -> Unit = {},
    onApplyClick: (JobOffers) -> Unit = {}
) {
    val sampleJobs = listOf(
        JobOffers(
            1,
            stringResource(id = R.string.title1),
            stringResource(id = R.string.company1),
            stringResource(id = R.string.location1)
        ),
        JobOffers(
            2,
            stringResource(id = R.string.title2),
            stringResource(id = R.string.company2),
            stringResource(id = R.string.location2)
        ),
        JobOffers(
            3,
            stringResource(id = R.string.title3),
            stringResource(id = R.string.company3),
            stringResource(id = R.string.location3)
        )
    )

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
                        text = stringResource(id = R.string.jop_offer),
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(id = R.string.available_jobs),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = AtrDarkText
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            items(sampleJobs) { job ->
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = AtrBackgroundGray),
                    border = BorderStroke(1.dp, AtrCardOutline),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(AtrSurfaceWhite, RoundedCornerShape(10.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    Icons.Default.Work,
                                    contentDescription = null,
                                    tint = AtrOrangePrimary
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = job.title,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = AtrDarkText
                                )
                                Text(
                                    text = "${job.company} • ${job.location}",
                                    fontSize = 13.sp,
                                    color = AtrDarkText.copy(alpha = 0.6f)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Button(
                            onClick = { onApplyClick(job) },
                            modifier = Modifier.align(Alignment.End),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = AtrOrangePrimary)
                        ) {
                            Text(
                                text = stringResource(id = R.string.apply_and_upload_cv),
                                fontSize = 13.sp,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}