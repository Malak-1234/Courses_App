package com.example.forgetpassword.components

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import java.io.File

fun openCamera(
    context: Context,
    cameraLauncher: ManagedActivityResultLauncher<Uri, Boolean>,
    cameraPermissionLauncher: ManagedActivityResultLauncher<String, Boolean>,
    onUriCreated: (Uri) -> Unit
) {
    val permissionCheck = ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.CAMERA
    )

    if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
        try {
            val photoFile = File(context.cacheDir, "profile_temp_${System.currentTimeMillis()}.jpg")
            val uri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.provider",
                photoFile
            )
            onUriCreated(uri)
            cameraLauncher.launch(uri)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    } else {
        cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
    }
}