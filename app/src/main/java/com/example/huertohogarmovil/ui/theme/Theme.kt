package com.example.huertohogarmovil.ui.theme


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = VerdeEsmeralda,
    onPrimary = Color.White,
    secondary = AmarilloMostaza,
    onSecondary = Color.Black,
    surface = BlancoSuave,
    onSurface = GrisOscuro,
    tertiary = MarronClaro,
)

@Composable
fun HuertoHogarMovilTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = androidx.compose.material3.Typography(),
        content = content
    )
}