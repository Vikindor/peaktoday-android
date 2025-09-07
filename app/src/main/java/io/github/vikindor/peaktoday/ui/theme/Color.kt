package io.github.vikindor.peaktoday.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val SandLight      = Color(0xFFF1E3C6)
val SandMuted      = Color(0xFFE5D4B5)
val WoodLight      = Color(0xFFE0B56A)
val WoodDark       = Color(0xFFB78843)
val Ink            = Color(0xFF2E2E2E)
val InkSoft        = Color(0xFF4E3B2B)
val AccentGreen    = Color(0xFF4E7B4B)
val ErrorRust      = Color(0xFF9B3D2F)

val PeakLightColors = lightColorScheme(
    primary = Ink,
    onPrimary = SandLight,
    secondary = WoodDark,
    onSecondary = Ink,
    background = SandLight,
    onBackground = Ink,
    surface = SandMuted,
    onSurface = Ink,
    surfaceVariant = WoodLight,
    onSurfaceVariant = Ink,
    error = ErrorRust,
    onError = Color.White
)

val PeakDarkColors = darkColorScheme(
    primary = SandLight,
    onPrimary = Ink,
    secondary = WoodLight,
    onSecondary = Ink,
    background = Color(0xFF1F1A15),
    onBackground = SandLight,
    surface = Color(0xFF2A241E),
    onSurface = SandLight,
    surfaceVariant = WoodDark,
    onSurfaceVariant = Ink,
    error = ErrorRust,
    onError = Color.White
)
