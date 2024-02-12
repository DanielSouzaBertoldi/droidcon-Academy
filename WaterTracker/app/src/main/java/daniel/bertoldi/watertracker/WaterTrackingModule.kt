package daniel.bertoldi.watertracker

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class WaterTrackingModule {

    @ActivityScoped
    @Provides
    fun providesPreferencesHelper(
        @ApplicationContext context: Context,
    ) = PreferencesHelper(context)
}