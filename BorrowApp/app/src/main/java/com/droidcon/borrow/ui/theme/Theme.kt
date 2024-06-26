package com.droidcon.borrow.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.staticCompositionLocalOf

private val LightColors = lightColors(
  primary = md_theme_light_primary,
  onPrimary = md_theme_light_onPrimary,
  secondary = md_theme_light_secondary,
  onSecondary = md_theme_light_onSecondary,
  background = md_theme_light_background,
  onBackground = md_theme_light_onBackground,
  surface = md_theme_light_surface,
  onSurface = md_theme_light_onSurface,
)

private val DarkColors = darkColors(
  primary = md_theme_dark_primary,
  onPrimary = md_theme_dark_onPrimary,
  secondary = md_theme_dark_secondary,
  onSecondary = md_theme_dark_onSecondary,
  background = md_theme_dark_background,
  onBackground = md_theme_dark_onBackground,
  surface = md_theme_dark_surface,
  onSurface = md_theme_dark_onSurface,
)

enum class Theme {
  LIGHT,
  DARK,
  FOLLOW_SYSTEM;
}

val LocalTheme = staticCompositionLocalOf<MutableState<Theme>> {
  error("Theme not provided")
}

@Composable
fun BorrowTheme(
  theme: Theme = Theme.FOLLOW_SYSTEM,
  content: @Composable () -> Unit,
) {
  val colors = when (theme) {
    Theme.LIGHT -> LightColors
    Theme.DARK -> DarkColors
    Theme.FOLLOW_SYSTEM -> if (isSystemInDarkTheme()) DarkColors else LightColors
  }

  MaterialTheme(
    colors = colors,
    typography = Typography,
    shapes = Shapes,
    content = content
  )
}