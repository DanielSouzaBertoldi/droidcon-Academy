package daniel.bertoldi.watertracker

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

@HiltAndroidTest
@UninstallModules(WaterTrackingModule::class)
@RunWith(AndroidJUnit4::class)
class WaterTrackerTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)
    @get:Rule
    val composeTestRule = createComposeRule()

    @BindValue @JvmField
    val preferencesHelper = mock<PreferencesHelper>()

    @Before
    fun init() = hiltRule.inject()

    /*
    * XML test version (it won't work since I'm using Compose
    * */
    @Test
    fun waterCountDisplays() {
        val waterIntake = 5

        whenever(preferencesHelper.getWaterIntake()).doReturn(waterIntake)

        ActivityScenario.launch(WaterTrackerActivity::class.java)

        onView(withId(R.id.compose))
            .check(matches(withText(waterIntake.toString())))
    }

    /*
    * This should be working but it isn't, Hilt gets lost when trying to find which
    * Fragment/Activity it's attached to when inflating a view inside a Composable. Hmm...
    * */
    @Test
    fun waterCountDisplaysCompose() {
        val waterIntake = 5

        whenever(preferencesHelper.getWaterIntake()).doReturn(waterIntake)

        composeTestRule.setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                WaterTracker(MutableStateFlow(0).collectAsState()) {
                    preferencesHelper.incrementWaterIntake()
                }
            }
        }

        composeTestRule.onRoot().printToLog("TAG")
    }
}