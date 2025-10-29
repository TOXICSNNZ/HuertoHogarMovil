package com.example.huertohogarmovil.ui.permission

import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext

@Composable
fun PermissionRequiredScreen(
    permission: String,
    onGranted: () -> Unit
) {
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { ok ->
        if (ok) onGranted()
    }
    Button(onClick = { launcher.launch(permission) }) {
        Text("Dar permiso")
    }
}

@Composable
fun WithPermission(permission: String, content: @Composable () -> Unit) {
    val ctx = LocalContext.current
    var granted by remember { mutableStateOf(ctx.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED) }

    if (!granted) {
        PermissionRequiredScreen(permission) { granted = true }
    } else {
        Surface { content() }
    }
}
