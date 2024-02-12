package daniel.bertoldi.watertracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import daniel.bertoldi.watertracker.databinding.FragmentLayoutBinding
import daniel.bertoldi.watertracker.ui.theme.WaterTrackerTheme
import kotlinx.coroutines.flow.MutableStateFlow

@AndroidEntryPoint
class WaterTrackingFragment : Fragment() {

    private val viewModel by viewModels<WaterTrackingViewModel>()

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
                WaterTracker(viewModel.flow.collectAsState(), viewModel::incrementWaterIntake)
            }
        }
    }.root
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
        val context = LocalContext.current
        AndroidView(
            modifier = Modifier.fillMaxWidth(),
            factory = { WaterCountTextView(context, null, 0) },
            update = {
                // I know, a bit of a tiny hack here.
                // The idea is that the CustomView is capable of already setting it's initial count,
                // which is the case when we init the view in the `factory` lambda. However, the
                // value in `waterCount` is initialized as 0, and its value is passed down to this
                // `update` lambda which we need to force the CustomView to update once the user
                // clicks in the Composable button below. This `if` just makes sure that the counter
                // is always incrementing, which is enough for this tiny project.
                if (it.text.toString().toInt() < waterCount.value) {
                    it.text = waterCount.value.toString()
                }
            }
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