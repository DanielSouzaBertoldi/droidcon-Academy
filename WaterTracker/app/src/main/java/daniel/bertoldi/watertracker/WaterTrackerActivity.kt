package daniel.bertoldi.watertracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import dagger.hilt.android.AndroidEntryPoint
import daniel.bertoldi.watertracker.ui.theme.WaterTrackerTheme
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@AndroidEntryPoint
class WaterTrackerActivity : ComponentActivity(), WaterIntakeSharedPrefsListener {
    private var waterCount = MutableStateFlow(0)
    @Inject lateinit var preferencesHelper: PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferencesHelper.subscribeToWaterIntakeChanges(this)
        waterCount.value = preferencesHelper.getWaterIntake()
        setContent {
            WaterTrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WaterTracker(waterCount.collectAsState(), ::incrementWaterIntake)
                }
            }
        }
    }

    override fun onDestroy() {
        preferencesHelper.unSubscribeToWaterIntakeChanges()
        super.onDestroy()
    }

    override fun onWaterIntakeChanged(intake: Int) {
        waterCount.value = intake
    }

    private fun incrementWaterIntake() {
        preferencesHelper.incrementWaterIntake()
    }
}

@Composable
fun WaterTracker(
    waterCount: State<Int>,
    incrementWaterIntake: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Text(
            color = Color.Gray,
            text = "${waterCount.value} cups!",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
        )
        Button(
            onClick = { incrementWaterIntake() },
        ) {
            Text(text = "Track!")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WaterTrackerTheme {
        WaterTracker(MutableStateFlow(0).collectAsState()) {}
    }
}