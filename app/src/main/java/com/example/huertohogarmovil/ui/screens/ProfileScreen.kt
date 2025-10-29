package com.example.huertohogarmovil.ui.screens

import android.Manifest
import android.net.Uri
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.lifecycle.awaitInstance
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.LifecycleOwner
import coil.compose.AsyncImage
import com.example.huertohogarmovil.ui.permission.WithPermission
import com.example.huertohogarmovil.viewmodel.UserViewModel
import java.io.File

@Composable
fun ProfileScreen(userVM: UserViewModel) {
    val user = userVM.currentUser.collectAsState().value
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Perfil", style = MaterialTheme.typography.headlineSmall)

        if (user?.photoUri != null) {
            AsyncImage(
                model = user.photoUri,
                contentDescription = "Foto de perfil",
                modifier = Modifier
                    .size(140.dp)
                    .clip(CircleShape)
            )
        } else {
            Box(
                modifier = Modifier
                    .size(140.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Text("Sin foto", style = MaterialTheme.typography.bodyMedium)
            }
        }

        Card {
            Column(
                Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text("Nombre: ${user?.name ?: "—"}")
                Text("Correo: ${user?.email ?: "—"}")
                Text("Dirección: ${user?.address ?: "—"}")
                Text("Foto guardada: ${if (user?.photoUri != null) "Sí" else "No"}")
            }
        }
        
        WithPermission(permission = Manifest.permission.CAMERA) {
            CameraSection { uri ->
                val current = user
                val name = current?.name ?: "Usuario"
                val email = current?.email ?: "correo@dominio.com"
                val pass = current?.password ?: "123456"
                val addr = current?.address ?: "Sin dirección"
                userVM.saveUser(name, email, pass, addr, photoUri = uri.toString())
            }
        }
    }
}

@Composable
private fun CameraSection(onImageSaved: (Uri) -> Unit) {
    val context = LocalContext.current
    val previewUseCase = remember { Preview.Builder().build() }
    val imageCaptureUseCase = remember { ImageCapture.Builder().build() }
    var cameraProvider by remember { mutableStateOf<ProcessCameraProvider?>(null) }
    var lensFacing by remember { mutableStateOf(CameraSelector.LENS_FACING_BACK) }

    LaunchedEffect(Unit) {
        cameraProvider = ProcessCameraProvider.awaitInstance(context)
        bind(previewUseCase, imageCaptureUseCase, cameraProvider, lensFacing, context as LifecycleOwner)
    }
    LaunchedEffect(lensFacing) {
        bind(previewUseCase, imageCaptureUseCase, cameraProvider, lensFacing, context as LifecycleOwner)
    }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .clip(MaterialTheme.shapes.medium),
            factory = { ctx ->
                PreviewView(ctx).also { pv -> previewUseCase.setSurfaceProvider(pv.surfaceProvider) }
            }
        )
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedButton(onClick = { lensFacing = CameraSelector.LENS_FACING_FRONT }) { Text("Frontal") }
            OutlinedButton(onClick = { lensFacing = CameraSelector.LENS_FACING_BACK }) { Text("Trasera") }
        }
        Button(
            onClick = {
                val file = File(context.externalCacheDir, "profile.jpg")
                val uri = FileProvider.getUriForFile(context, "com.example.huertohogarmovil.fileprovider", file)
                val opts = ImageCapture.OutputFileOptions.Builder(file).build()
                imageCaptureUseCase.takePicture(
                    opts,
                    ContextCompat.getMainExecutor(context),
                    object : ImageCapture.OnImageSavedCallback {
                        override fun onImageSaved(output: ImageCapture.OutputFileResults) { onImageSaved(uri) }
                        override fun onError(exception: ImageCaptureException) {}
                    }
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Tomar foto de perfil") }
    }
}

private fun bind(
    preview: Preview,
    imageCapture: ImageCapture,
    provider: ProcessCameraProvider?,
    lensFacing: Int,
    owner: LifecycleOwner
) {
    provider ?: return
    val selector = CameraSelector.Builder().requireLensFacing(lensFacing).build()
    provider.unbindAll()
    provider.bindToLifecycle(owner, selector, preview, imageCapture)
}
