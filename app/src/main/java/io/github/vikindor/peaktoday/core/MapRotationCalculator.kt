package io.github.vikindor.peaktoday.core

import java.time.Duration
import java.time.ZonedDateTime

class MapRotationCalculator(private val anchor: Anchor) {

    private val daySec = 86_400L

    private fun daysSinceAnchor(nowUtc: ZonedDateTime): Long {
        val seconds = Duration.between(anchor.switchUtc, nowUtc).seconds
        return Math.floorDiv(seconds, daySec)
    }

    fun currentMap(nowUtc: ZonedDateTime): MapType {
        val days = daysSinceAnchor(nowUtc)
        return if (days % 2L == 0L) anchor.mapAfterSwitch else other(anchor.mapAfterSwitch)
    }

    fun nextSwitchUtc(nowUtc: ZonedDateTime): ZonedDateTime {
        val days = daysSinceAnchor(nowUtc)
        return anchor.switchUtc.plusSeconds((days + 1) * daySec)
    }

    fun countdown(nowUtc: ZonedDateTime) =
        Duration.between(nowUtc, nextSwitchUtc(nowUtc)).coerceAtLeast(Duration.ZERO)

    private fun other(m: MapType) =
        if (m == MapType.Mesa) MapType.Alpine else MapType.Mesa
}
