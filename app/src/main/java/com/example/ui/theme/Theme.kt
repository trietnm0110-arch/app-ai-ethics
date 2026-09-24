package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Indigo400,
    onPrimary = Navy950,
    primaryContainer = Navy800,
    onPrimaryContainer = Indigo300,
    secondary = Purple400,
    onSecondary = Navy950,
    secondaryContainer = Navy800,
    onSecondaryContainer = Purple400,
    tertiary = Cyan400,
    onTertiary = Navy950,
    background = Navy950,
    onBackground = TextPrimary,
    surface = Navy900,
    onSurface = TextPrimary,
    surfaceVariant = Navy800,
    onSurfaceVariant = TextSecondary,
    outline = Navy700,
    outlineVariant = Color(0x30818CF8),
    error = Rose400,
    onError = Navy950
)

@Composable
fun MyApplicationTheme(
    content: @Composable () -> Unit
) {
    // We enforce our polished semi-dark/dark visual novel academic theme
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}
