package io.github.vikindor.peaktoday.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun PeakTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) PeakDarkColors else PeakLightColors
    MaterialTheme(
        colorScheme = PeakLightColors,
        typography = PeakTypography,
        content = content
    )
}
