package daniel.bertoldi.watertracker

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class WaterTrackingViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val preferencesHelper: PreferencesHelper,
) : ViewModel(), WaterIntakeSharedPrefsListener {
    private val _flow = MutableStateFlow(0)
    val flow = _flow

    init {
        preferencesHelper.subscribeToWaterIntakeChanges(this)
    }

    override fun onCleared() {
        preferencesHelper.unSubscribeToWaterIntakeChanges()
        super.onCleared()
    }

    fun incrementWaterIntake() {
        preferencesHelper.incrementWaterIntake()
    }

    override fun onWaterIntakeChanged(intake: Int) {
        _flow.value = intake
    }
}