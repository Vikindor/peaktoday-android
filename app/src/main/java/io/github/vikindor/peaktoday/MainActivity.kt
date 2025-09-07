package io.github.vikindor.peaktoday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import io.github.vikindor.peaktoday.model.MapClockViewModel
import io.github.vikindor.peaktoday.ui.AppRoot
import io.github.vikindor.peaktoday.ui.MainScreen
import io.github.vikindor.peaktoday.ui.theme.PeakTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            PeakTheme {
                val vm = remember { MapClockViewModel() }
                val state by vm.state
                AppRoot( mapsScreen = { MainScreen(state) } )
            }
        }
    }
}
