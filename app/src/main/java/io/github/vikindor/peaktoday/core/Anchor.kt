package io.github.vikindor.peaktoday.core

import java.time.ZoneOffset
import java.time.ZonedDateTime

data class Anchor(
    val switchUtc: ZonedDateTime,
    val mapAfterSwitch: MapType
)

fun defaultAnchor(): Anchor =
    Anchor(
        switchUtc = ZonedDateTime.of(2025, 9, 1, 17, 0, 0, 0, ZoneOffset.UTC),
        mapAfterSwitch = MapType.Alpine
    )
