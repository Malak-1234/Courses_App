package com.example.forgetpassword.screens

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import coil.compose.AsyncImage
import com.example.forgetpassword.R
import com.example.forgetpassword.components.CustomTextField
import com.example.forgetpassword.data.DummyData
import com.example.forgetpassword.screens.ui.theme.AtrBackgroundGray
import com.example.forgetpassword.screens.ui.theme.AtrSurfaceWhite
import java.io.File


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    userName: String = "",
    userEmail: String = "",
    onBackClick: () -> Unit = {}
) {
    val context = LocalContext.current

    val currentUser = remember(userEmail, userName) {
        DummyData.students.find { it.email == userEmail || it.name == userName }
            ?: DummyData.students.firstOrNull()
    }

    var name by remember(currentUser) { mutableStateOf(currentUser?.name ?: userName) }
    var email by remember(currentUser) { mutableStateOf(currentUser?.email ?: userEmail) }
    var password by remember(currentUser) { mutableStateOf(currentUser?.password ?: "") }
    var department by remember(currentUser) { mutableStateOf(currentUser?.department ?: "") }
    var address by remember(currentUser) { mutableStateOf(currentUser?.address ?: "") }

    var isPasswordVisible by remember { mutableStateOf(false) }

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var showImageSourceDialog by remember { mutableStateOf(false) }
    var tempCameraUri by remember { mutableStateOf<Uri?>(null) }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let { imageUri = it }
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            imageUri = tempCameraUri
        }
    }

    Scaffold(
        containerColor = AtrSurfaceWhite,
        contentWindowInsets = WindowInsets.systemBars
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back),
                        tint = AtrDarkText
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = stringResource(R.string.edit_profile),
                    color = AtrDarkText,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.weight(1.3f))
            }

            Box(
                modifier = Modifier
                    .size(130.dp)
                    .clickable { showImageSourceDialog = true },
                contentAlignment = Alignment.BottomEnd
            ) {
                if (imageUri != null) {
                    AsyncImage(
                        model = imageUri,
                        contentDescription = stringResource(R.string.profile_picture),
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                            .border(2.dp, AtrCardOutline, CircleShape),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                            .background(AtrBackgroundGray)
                            .border(2.dp, AtrCardOutline, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = stringResource(R.string.default_profile),
                            tint = AtrDarkText.copy(alpha = 0.4f),
                            modifier = Modifier.size(70.dp)
                        )
                    }
                }

                Surface(
                    modifier = Modifier.size(36.dp),
                    shape = CircleShape,
                    color = AtrOrangePrimary
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.CameraAlt,
                            contentDescription = stringResource(R.string.change_image),
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            CustomTextField(
                label = stringResource(R.string.label_name),
                value = name,
                onValueChange = { name = it }
            )

            CustomTextField(
                label = stringResource(R.string.label_email),
                value = email,
                onValueChange = { email = it }
            )

            CustomTextField(
                label = stringResource(R.string.label_password),
                value = password,
                onValueChange = { password = it },
                isPassword = !isPasswordVisible,
                trailingIcon = {
                    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                        Icon(
                            imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = "Toggle Password Visibility",
                            tint = AtrDarkText.copy(alpha = 0.6f)
                        )
                    }
                }
            )

            CustomTextField(
                label = stringResource(R.string.label_department),
                value = department,
                onValueChange = { department = it },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        tint = AtrDarkText
                    )
                }
            )

            CustomTextField(
                label = stringResource(R.string.label_address),
                value = address,
                onValueChange = { address = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    currentUser?.let { student ->
                        val index = DummyData.students.indexOf(student)
                        if (index != -1) {
                            DummyData.students[index] = student.copy(
                                name = name,
                                email = email,
                                password = password,
                                department = department,
                                address = address
                            )
                            Toast.makeText(context, "Changes saved successfully", Toast.LENGTH_SHORT).show()
                            onBackClick()
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = AtrOrangePrimary)
            ) {
                Text(
                    text = stringResource(R.string.save_changes),
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.ime))
        }
    }

    if (showImageSourceDialog) {
        ModalBottomSheet(
            onDismissRequest = { showImageSourceDialog = false },
            containerColor = AtrSurfaceWhite
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = stringResource(R.string.change_profile_picture),
                    color = AtrDarkText,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            showImageSourceDialog = false
                            galleryLauncher.launch("image/*")
                        }
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.PhotoLibrary,
                        contentDescription = stringResource(R.string.gallery),
                        tint = AtrOrangePrimary
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = stringResource(R.string.choose_from_gallery),
                        color = AtrDarkText,
                        fontSize = 16.sp
                    )
                }

                HorizontalDivider(color = AtrCardOutline)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            showImageSourceDialog = false
                            try {
                                val photoFile = File(
                                    context.cacheDir,
                                    "profile_temp_${System.currentTimeMillis()}.jpg"
                                )

                                val uri = FileProvider.getUriForFile(
                                    context,
                                    "${context.packageName}.fileprovider",
                                    photoFile
                                )
                                tempCameraUri = uri
                                cameraLauncher.launch(uri)
                            } catch (e: Exception) {
                                e.printStackTrace()
                                Toast.makeText(
                                    context,
                                    "Error launching camera: ${e.localizedMessage}",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.CameraAlt,
                        contentDescription = stringResource(R.string.camera),
                        tint = AtrOrangePrimary
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = stringResource(R.string.take_a_new_photo),
                        color = AtrDarkText,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}