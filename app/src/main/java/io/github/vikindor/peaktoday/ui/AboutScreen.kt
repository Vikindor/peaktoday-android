package io.github.vikindor.peaktoday.ui

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import io.github.vikindor.peaktoday.R
import io.github.vikindor.peaktoday.ui.theme.WoodDark
import io.github.vikindor.peaktoday.BuildConfig
import androidx.core.net.toUri

@Composable
fun AboutScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.peak_bg),
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        )

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

        // Content
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .padding(top = 36.dp, bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = "About",
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

                Spacer(Modifier.height(16.dp))
            }

            item {
                WoodSign(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(vertical = 18.dp, horizontal = 20.dp)
                ) {
                    SignTitle("APP")
                    Spacer(Modifier.height(8.dp))
                    SignLine("Version: ${BuildConfig.VERSION_NAME}")
                }

                Spacer(Modifier.height(16.dp))
            }

            item {
                WoodSign(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(vertical = 18.dp, horizontal = 20.dp)
                ) {
                    SignTitle("DISCLAIMER")
                    Spacer(Modifier.height(8.dp))
                    SignLine("This is a non-commercial fan-made application.")
                    SignLine("All rights to the game \"PEAK\" and its assets belong to Landfall Games:")
                    ClickableSignLine("https://landfall.se/peak", "https://landfall.se/peak")
                    SignLine("This app is not affiliated with or endorsed by Landfall Games.")
                    SignLine("No copyright or trademark infringement is intended.")
                }

                Spacer(Modifier.height(16.dp))
            }

            item {
                WoodSign(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(vertical = 18.dp, horizontal = 20.dp)
                ) {
                    SignTitle("AUTHOR")
                    Spacer(Modifier.height(8.dp))
                    SignLine("Vikindor")
                    ClickableSignLine("vikindor.github.io", "https://vikindor.github.io/")
                }
            }
        }
    }
}

/* ------------------- Helper UI ------------------- */

@Composable
fun ClickableSignLine(
    label: String,
    url: String
) {
    val context = LocalContext.current
    Text(
        text = label,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.primary,
        textDecoration = TextDecoration.Underline,
        modifier = Modifier.clickable {
            val intent = Intent(Intent.ACTION_VIEW, url.toUri())
            context.startActivity(intent)
        }
    )
}
