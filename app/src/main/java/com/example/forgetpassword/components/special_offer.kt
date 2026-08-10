package com.example.forgetpassword.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.forgetpassword.R
import com.example.forgetpassword.data.DummyData.dummyOffers
import com.example.forgetpassword.models.SpecialOfferItem
import com.example.forgetpassword.screens.ui.theme.AtrDarkText
import com.example.forgetpassword.screens.ui.theme.AtrOrangeDark
import com.example.forgetpassword.screens.ui.theme.AtrOrangePrimary
import com.example.forgetpassword.screens.ui.theme.AtrSurfaceWhite
import kotlinx.coroutines.delay


@Composable
fun SpecialOffer(
    offers: List<SpecialOfferItem> = dummyOffers,
    onOfferClick: (SpecialOfferItem) -> Unit = {}
) {
    if (offers.isEmpty()) return

    val pagerState = rememberPagerState(pageCount = { offers.size })

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            if (!pagerState.isScrollInProgress) {
                val nextPage = (pagerState.currentPage + 1) % offers.size
                pagerState.animateScrollToPage(nextPage)
            }
        }
    }

    HorizontalPager(
        state = pagerState,
        pageSpacing = 12.dp,
        modifier = Modifier.fillMaxWidth()
    ) { page ->
        val currentOffer = offers[page]

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .clickable { onOfferClick(currentOffer) },
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = AtrOrangePrimary)
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.65f)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.special_offers),
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Column {
                        Text(
                            text = currentOffer.title,
                            color = Color.White,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = currentOffer.subtitle,
                            color = Color.White.copy(alpha = 0.85f),
                            fontSize = 12.sp
                        )
                    }
                    Button(
                        onClick = { onOfferClick(currentOffer) },
                        colors = ButtonDefaults.buttonColors(containerColor = AtrSurfaceWhite),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.explore_now),
                            color = AtrOrangeDark,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }

                Image(
                    painter = painterResource(id = currentOffer.imageRes),
                    contentDescription = stringResource(id = R.string.banner_image),
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .fillMaxWidth(0.35f)
                        .fillMaxHeight(0.85f),
                    contentScale = ContentScale.Fit
                )

                Surface(
                    color = AtrDarkText,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(16.dp)
                ) {
                    Text(
                        text = currentOffer.discountTag,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                        modifier = Modifier.padding(6.dp)
                    )
                }
            }
        }
    }
}