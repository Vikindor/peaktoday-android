package io.github.vikindor.peaktoday.model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.vikindor.peaktoday.core.*
import io.github.vikindor.peaktoday.util.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.ZonedDateTime

data class UiState(
    val nowUtc: ZonedDateTime,
    val currentMap: MapType,
    val nextSwitchUtc: ZonedDateTime,
    val countdown: Duration
)

class MapClockViewModel(
    private val time: TimeProvider = SystemTimeProvider(),
    private val calc: MapRotationCalculator = MapRotationCalculator(defaultAnchor())
) : ViewModel() {

    private val _state = mutableStateOf(makeState(time.nowUtc()))
    val state: State<UiState> = _state

    init {
        viewModelScope.launch {
            while (isActive) {
                _state.value = makeState(time.nowUtc())
                delay(1000)
            }
        }
    }

    private fun makeState(now: ZonedDateTime) = UiState(
        nowUtc = now,
        currentMap = calc.currentMap(now),
        nextSwitchUtc = calc.nextSwitchUtc(now),
        countdown = calc.countdown(now)
    )
}
