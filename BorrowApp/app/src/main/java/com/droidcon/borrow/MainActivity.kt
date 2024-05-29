package com.droidcon.borrow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.droidcon.borrow.navigation.AppNavigation
import com.droidcon.borrow.ui.theme.BorrowTheme
import com.droidcon.borrow.ui.theme.LocalTheme
import com.droidcon.borrow.ui.theme.Theme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import org.koin.androidx.viewmodel.ext.android.viewModel

@OptIn(ExperimentalAnimationApi::class)
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      val navController = rememberAnimatedNavController()
      val borrowViewModel: BorrowViewModel by viewModel()
      val theme = remember { mutableStateOf(borrowViewModel.theme) }

      CompositionLocalProvider(LocalTheme provides theme) {
        borrowViewModel.theme = theme.value
        BorrowTheme(theme = theme.value) {
          val color = when(theme.value) {
            Theme.LIGHT -> R.color.statusBar
            Theme.DARK -> R.color.statusBarDark
            Theme.FOLLOW_SYSTEM -> if (isSystemInDarkTheme()) R.color.statusBarDark else R.color.statusBar
          }
          window.statusBarColor = ContextCompat.getColor(this, color)
          AppNavigation(navController = navController, viewModel = borrowViewModel)
        }
      }
    }
  }
}

@Composable
fun Greeting(name: String) {
  Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  BorrowTheme {
    Greeting("Android")
  }
}