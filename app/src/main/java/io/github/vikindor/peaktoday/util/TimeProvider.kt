package io.github.vikindor.peaktoday.util

import java.time.ZoneOffset
import java.time.ZonedDateTime

interface TimeProvider { fun nowUtc(): ZonedDateTime }

class SystemTimeProvider : TimeProvider {
    override fun nowUtc(): ZonedDateTime = ZonedDateTime.now(ZoneOffset.UTC)
}
