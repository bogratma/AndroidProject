package com.simpleplayer.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val LightColors = lightColorScheme(
    primary = Color(0xFF6950A3),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFEBDDFF),
    onPrimaryContainer = Color(0xFF39216A),
    background = Color(0xFFFFFBFF),
    onBackground = Color(0xFF252229),
    surface = Color(0xFFFFFBFF),
    onSurface = Color(0xFF252229),
    surfaceContainer = Color(0xFFF2ECF6),
    onSurfaceVariant = Color(0xFF68616F),
    error = Color(0xFFBA251B),
    onError = Color.White
)
val DarkColors = darkColorScheme(
    primary = Color(0xFFD0BCFF),
    onPrimary = Color(0xFF381E72),
    primaryContainer = Color(0xFF4F378B),
    onPrimaryContainer = Color(0xFFEADDFF),
    background = Color(0xFF141218),
    onBackground = Color(0xFFE6E0E9),
    surface = Color(0xFF141218),
    onSurface = Color(0xFFE6E0E9),
    surfaceContainer = Color(0xFF211F26),
    onSurfaceVariant = Color(0xFFCAC4D0),
    error = Color(0xFFF2B8B5),
    onError = Color(0xFF601410)
)