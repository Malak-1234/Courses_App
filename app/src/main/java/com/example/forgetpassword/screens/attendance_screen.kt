package com.example.forgetpassword.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.forgetpassword.R
import com.example.forgetpassword.models.AttendanceState
import com.example.forgetpassword.screens.ui.theme.AtrCardOutline
import com.example.forgetpassword.screens.ui.theme.AtrDarkText
import com.example.forgetpassword.screens.ui.theme.AtrOrangePrimary
import com.example.forgetpassword.screens.ui.theme.AtrSurfaceWhite
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttendanceScreen(
    onBackClick: () -> Unit
) {
    val currentDateText = remember {
        LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE, MMM dd"))
    }

    var currentTimeText by remember { mutableStateOf("") }
    val timeFormatter = remember { DateTimeFormatter.ofPattern("hh:mm:ss a") }
    val shortTimeFormatter = remember { DateTimeFormatter.ofPattern("hh:mm a") }

    LaunchedEffect(Unit) {
        while (true) {
            currentTimeText = LocalTime.now().format(timeFormatter)
            delay(1000)
        }
    }

    var isCheckedIn by remember { mutableStateOf(AttendanceState.isCheckedIn) }
    var isCheckedOut by remember { mutableStateOf(AttendanceState.isCheckedOut) }
    var checkInTimeText by remember { mutableStateOf(AttendanceState.checkInTimeText) }
    var checkOutTimeText by remember { mutableStateOf(AttendanceState.checkOutTimeText) }
    var workingHoursText by remember { mutableStateOf(AttendanceState.workingHoursText) }

    val progress = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()

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
                        text = stringResource(id = R.string.attendance),
                        fontSize = 18.sp,
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
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = if (isCheckedOut) workingHoursText else currentTimeText,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = AtrDarkText
            )

            Text(
                text = currentDateText,
                color = AtrDarkText.copy(alpha = 0.6f),
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(40.dp))

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(175.dp)
            ) {
                CircularProgressIndicator(
                    progress = { progress.value },
                    modifier = Modifier.fillMaxSize(),
                    color = if (isCheckedIn) Color(0xFF4CAF50) else AtrOrangePrimary,
                    strokeWidth = 6.dp,
                    trackColor = AtrCardOutline
                )

                Surface(
                    modifier = Modifier
                        .size(160.dp)
                        .pointerInput(isCheckedIn, isCheckedOut) {
                            if (isCheckedOut) return@pointerInput

                            detectTapGestures(
                                onPress = {
                                    val animationJob = scope.launch {
                                        progress.animateTo(1f, animationSpec = tween(1000))
                                    }

                                    val isReleased = tryAwaitRelease()

                                    if (!isReleased || progress.value < 1f) {
                                        animationJob.cancel()
                                        scope.launch { progress.animateTo(0f) }
                                    } else {
                                        val now = LocalTime.now()

                                        if (!isCheckedIn) {
                                            isCheckedIn = true
                                            checkInTimeText = now.format(shortTimeFormatter)

                                            AttendanceState.isCheckedIn = true
                                            AttendanceState.checkInTimeText = checkInTimeText
                                            AttendanceState.checkInTimeRaw = now
                                        }
                                        else if (!isCheckedOut) {
                                            isCheckedOut = true
                                            checkOutTimeText = now.format(shortTimeFormatter)

                                            AttendanceState.checkInTimeRaw?.let { start ->
                                                val duration = Duration.between(start, now)
                                                val hours = duration.toHours()
                                                val minutes = duration.toMinutes() % 60
                                                workingHoursText = "${hours}h ${minutes}m"
                                            }

                                            AttendanceState.isCheckedOut = true
                                            AttendanceState.checkOutTimeText = checkOutTimeText
                                            AttendanceState.workingHoursText = workingHoursText
                                        }

                                        scope.launch { progress.snapTo(0f) }
                                    }
                                }
                            )
                        },
                    shape = CircleShape,
                    color = when {
                        isCheckedOut -> AtrDarkText.copy(alpha = 0.4f)
                        isCheckedIn -> Color(0xFF4CAF50)
                        else -> AtrOrangePrimary
                    },
                    shadowElevation = 8.dp
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.TouchApp,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(48.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = when {
                                isCheckedOut -> stringResource(id = R.string.done)
                                isCheckedIn -> stringResource(id = R.string.hold_to_check_out)
                                else -> stringResource(id = R.string.hold_to_check_in)
                            },
                            color = Color.White,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(id = R.string.location),
                color = AtrDarkText.copy(alpha = 0.6f),
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = checkInTimeText, fontWeight = FontWeight.Bold, color = AtrDarkText)
                    Text(text = stringResource(id = R.string.check_in), fontSize = 11.sp, color = AtrDarkText.copy(alpha = 0.6f))
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = checkOutTimeText, fontWeight = FontWeight.Bold, color = AtrDarkText)
                    Text(text = stringResource(id = R.string.check_out), fontSize = 11.sp, color = AtrDarkText.copy(alpha = 0.6f))
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = workingHoursText, fontWeight = FontWeight.Bold, color = AtrDarkText)
                    Text(text = stringResource(id = R.string.working_hrs), fontSize = 11.sp, color = AtrDarkText.copy(alpha = 0.6f))
                }
            }
        }
    }
}