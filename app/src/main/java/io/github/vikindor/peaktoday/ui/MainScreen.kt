package io.github.vikindor.peaktoday.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.vikindor.peaktoday.R
import io.github.vikindor.peaktoday.core.MapType
import io.github.vikindor.peaktoday.model.BiomeCard
import io.github.vikindor.peaktoday.model.UiState
import io.github.vikindor.peaktoday.ui.theme.WoodDark
import io.github.vikindor.peaktoday.ui.theme.WoodLight
import java.time.Duration
import java.time.format.DateTimeFormatter
import kotlin.math.max

@Composable
fun MainScreen(state: UiState) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Background image (fills height; crops width on phones; shows more on tablets)
        Image(
            painter = painterResource(R.drawable.peak_bg),
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        )

        // Optional overlay for readability
        Box(
            Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        0f to Color.Black.copy(alpha = 0.06f),
                        1f to Color.Black.copy(alpha = 0.14f)
                    )
                )
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .padding(top = 36.dp, bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item {
                Title(text = "Peak Today")
                Spacer(Modifier.height(16.dp))
            }

            val cards = listOf(
                BiomeCard("SHORE"),
                BiomeCard("TROPICS"),
                BiomeCard(
                    title = when (state.currentMap) {
                        MapType.Mesa -> "MESA"
                        MapType.Alpine -> "ALPINE"
                    },
                    showDetails = true
                ),
                BiomeCard("CALDERA"),
                BiomeCard("THE KILN")
            )

            items(cards) { card ->
                val nextSwitchText = if (card.showDetails) {
                    "Next switch: " + state.nextSwitchUtc.format(
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss 'UTC'")
                    )
                } else null

                val timeLeftText = if (card.showDetails) {
                    "Time left: ${formatHMS(state.countdown)}"
                } else null

                BiomeSign(
                    card = card,
                    nextSwitchText = nextSwitchText,
                    timeLeftText = timeLeftText
                )
            }
        }
    }
}

/* ------------------- Helper UI ------------------- */

@Composable
private fun Title(text: String) {
    androidx.compose.material3.Text(
        text = text,
        style = MaterialTheme.typography.titleLarge.copy(
            fontWeight = FontWeight.ExtraBold,
            shadow = Shadow(
                color = Color.Black.copy(alpha = 0.25f),
                offset = Offset(0f, 2f),
                blurRadius = 6f
            )
        ),
        color = WoodDark,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}

@Composable
internal fun SignTitle(text: String) {
    androidx.compose.material3.Text(
        text = text,
        style = MaterialTheme.typography.displayLarge,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}

@Composable
internal fun SignLine(text: String, bold: Boolean = false) {
    androidx.compose.material3.Text(
        text = text,
        style = if (bold)
            MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
        else
            MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}

/* ------------------- Wooden sign ------------------- */

@Composable
fun WoodSign(
    modifier: Modifier = Modifier,
    corner: Dp = 22.dp,
    elevation: Dp = 8.dp,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    content: @Composable ColumnScope.() -> Unit
) {
    val shape = RoundedCornerShape(corner)

    // Wood
    Box(
        modifier = modifier
            .shadow(elevation, shape, clip = false)
            .clip(shape)
            .background(
                Brush.verticalGradient(
                    0f to WoodLight,
                    0.6f to WoodLight,
                    1f to WoodDark
                )
            )
    ) {
        // Border & nails
        Canvas(modifier = Modifier.matchParentSize()) {
            drawRoundRect(
                color = WoodDark.copy(alpha = 0.8f),
                style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round),
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(corner.toPx(), corner.toPx())
            )
            val r = 5.5.dp.toPx()
            val pad = 14.dp.toPx()
            val nail = WoodDark
            drawCircle(color = nail, radius = r, center = Offset(pad, pad))
            drawCircle(color = Color.White.copy(alpha = 0.45f), radius = r * 0.45f, center = Offset(pad - r*0.25f, pad - r*0.25f))
            drawCircle(color = nail, radius = r, center = Offset(size.width - pad, pad))
            drawCircle(color = Color.White.copy(alpha = 0.45f), radius = r * 0.45f, center = Offset(size.width - pad - r*0.25f, pad - r*0.25f))
        }
        Column(
            modifier = Modifier.padding(contentPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) { content() }
    }
}

/* ------------------- Time utility ------------------- */

private fun formatHMS(d: Duration): String {
    val total = max(0L, d.seconds)
    val h = total / 3600
    val m = (total % 3600) / 60
    val s = total % 60
    return "%02d:%02d:%02d".format(h, m, s)
}
