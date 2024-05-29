package com.droidcon.borrow.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightColors = lightColors()

@Composable
fun BorrowTheme(content: @Composable () -> Unit) {
  val colors = LightColors

  MaterialTheme(
    colors = colors,
    typography = Typography,
    shapes = Shapes,
    content = content
  )
}