package com.example.huertohogarmovil.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.huertohogarmovil.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreen(onComenzar: () -> Unit) {
    Scaffold(topBar = { TopAppBar(title = { Text("HuertoHogar") }) }) { pad ->
        Column(
            Modifier.padding(pad).fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text("¡Bienvenido!", color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.titleLarge)
            Button(onClick = onComenzar) { Text("Comenzar") }
            Image(
                painter = painterResource(id = R.drawable.hero),
                contentDescription = "Logo App",
                modifier = Modifier.fillMaxWidth().height(150.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}
