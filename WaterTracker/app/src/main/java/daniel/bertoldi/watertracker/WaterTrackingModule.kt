package daniel.bertoldi.watertracker

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(FragmentComponent::class)
class WaterTrackingModule {

    @Provides
    fun providesPreferencesHelper(
        @ApplicationContext context: Context,
    ) = PreferencesHelper(context)
}