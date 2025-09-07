package io.github.vikindor.peaktoday.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.vikindor.peaktoday.model.BiomeCard
import io.github.vikindor.peaktoday.ui.theme.WoodDark
import androidx.compose.foundation.layout.padding

@Composable
internal fun BiomeSign(
    card: BiomeCard,
    nextSwitchText: String? = null,
    timeLeftText: String? = null
) {
    WoodSign(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        contentPadding = PaddingValues(vertical = 22.dp, horizontal = 20.dp)
    ) {
        SignTitle(card.title)

        if (card.showDetails) {
            Spacer(Modifier.height(10.dp))
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                color = WoodDark.copy(alpha = 0.5f),
                thickness = 1.dp
            )
            Spacer(Modifier.height(10.dp))
            if (!nextSwitchText.isNullOrEmpty()) {
                SignLine(nextSwitchText)
                Spacer(Modifier.height(6.dp))
            }
            if (!timeLeftText.isNullOrEmpty()) {
                SignLine(timeLeftText, bold = true)
            }
        }
    }
}
