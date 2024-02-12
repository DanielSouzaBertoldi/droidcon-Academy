package daniel.bertoldi.watertracker

import android.content.Context
import android.content.SharedPreferences

class PreferencesHelper constructor(context: Context) {
    private val preferences =
        context.getSharedPreferences("water_tracker_prefs", Context.MODE_PRIVATE)
    private val KEY_WATER_INTAKE = "KEY_WATER_INTAKE"
    private var waterIntakePrefsListener: WaterIntakeSharedPrefsListener? = null

    fun incrementWaterIntake() {
        val waterIntake = preferences.getInt(KEY_WATER_INTAKE, 0)
        preferences.edit().putInt(KEY_WATER_INTAKE, waterIntake + 1).apply()
    }

    fun getWaterIntake() = preferences.getInt(KEY_WATER_INTAKE, 0)

    fun subscribeToWaterIntakeChanges(listener: WaterIntakeSharedPrefsListener) {
        this.waterIntakePrefsListener = listener
        preferences.registerOnSharedPreferenceChangeListener(sharedPreferencesChangeListener)
    }

    fun unSubscribeToWaterIntakeChanges() {
        this.waterIntakePrefsListener = null
        preferences.unregisterOnSharedPreferenceChangeListener(sharedPreferencesChangeListener)
    }

    private val sharedPreferencesChangeListener =
        SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
            if (key == KEY_WATER_INTAKE) {
                waterIntakePrefsListener?.onWaterIntakeChanged(
                    sharedPreferences.getInt(KEY_WATER_INTAKE, 0)
                )
            }
        }
}