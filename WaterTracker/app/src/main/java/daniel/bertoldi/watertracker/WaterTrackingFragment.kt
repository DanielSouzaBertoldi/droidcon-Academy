package daniel.bertoldi.watertracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import daniel.bertoldi.watertracker.databinding.FragmentLayoutBinding
import daniel.bertoldi.watertracker.ui.theme.WaterTrackerTheme
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@AndroidEntryPoint
class WaterTrackingFragment : Fragment(), WaterIntakeSharedPrefsListener {
    private var waterCount = MutableStateFlow(0)
    @Inject lateinit var preferencesHelper: PreferencesHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentLayoutBinding.inflate(layoutInflater, container, false).apply {
        compose.setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                WaterTracker(waterCount.collectAsState(), ::incrementWaterIntake)
            }
        }
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        waterCount.value = preferencesHelper.getWaterIntake()
        preferencesHelper.subscribeToWaterIntakeChanges(this)
    }

    override fun onDestroyView() {
        preferencesHelper.unSubscribeToWaterIntakeChanges()
        super.onDestroyView()
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
fun WaterTrackerPreview() {
    WaterTrackerTheme {
        WaterTracker(MutableStateFlow(0).collectAsState()) {}
    }
}