package com.droidcon.borrow.ui.composables

import android.content.res.Configuration
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.droidcon.borrow.ui.theme.BorrowTheme

@Composable
fun BackTopBar(title: String, onBackPressed: () -> Unit) {
  TopAppBar(
    title = { Text(title) },
    modifier = Modifier,
    navigationIcon = {
      IconButton(onClick = {
        onBackPressed()
      }) {
        Icon(
          imageVector = Icons.Default.ArrowBack,
          contentDescription = "Back Icon",
        )
      }
    }
  )
}

@Preview
@Composable
private fun BackTopBarLightPreview() {
  BorrowTheme {
    BackTopBar(title = "My Title", onBackPressed = {})
  }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BackTopBarDarkPreview() {
  BorrowTheme {
    BackTopBar(title = "My Title", onBackPressed = {})
  }
}