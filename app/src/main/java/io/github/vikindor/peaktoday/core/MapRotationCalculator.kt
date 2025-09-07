package io.github.vikindor.peaktoday.core

import java.time.Duration
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

class MapRotationCalculator(private val anchor: Anchor) {

    fun currentMap(nowUtc: ZonedDateTime): MapType {
        val days = ChronoUnit.DAYS.between(anchor.switchUtc, nowUtc)
        return if (days % 2L == 0L) anchor.mapAfterSwitch else other(anchor.mapAfterSwitch)
    }

    fun nextSwitchUtc(nowUtc: ZonedDateTime): ZonedDateTime {
        val days = ChronoUnit.DAYS.between(anchor.switchUtc, nowUtc)
        val last = anchor.switchUtc.plusDays(days)
        return last.plusDays(1)
    }

    fun countdown(nowUtc: ZonedDateTime): Duration =
        Duration.between(nowUtc, nextSwitchUtc(nowUtc)).coerceAtLeast(Duration.ZERO)

    private fun other(m: MapType) = if (m == MapType.Mesa) MapType.Alpine else MapType.Mesa
}
