package com.droidcon.borrow.ui.composables

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.droidcon.borrow.ui.theme.BorrowTheme

@Composable
fun EmptyStateScreen() {
  Column(
    modifier = Modifier
      .fillMaxHeight()
      .fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {

    Icon(
      imageVector = Icons.Default.AccountBox,
      contentDescription = null,
      modifier = Modifier
        .size(60.dp, 60.dp),
      tint = MaterialTheme.colors.onBackground,
    )

    Spacer(
      modifier = Modifier
        .padding(top = 30.dp)
    )

    Text(
      text = "No items borrowed",
      modifier = Modifier
        .fillMaxWidth(),
      color = MaterialTheme.colors.onBackground,
      textAlign = TextAlign.Center,
      fontSize = 24.sp,
      fontWeight = FontWeight.Bold
    )
  }
}

@Preview
@Composable
private fun EmptyStateScreenLightPreview() {
  BorrowTheme {
    EmptyStateScreen()
  }
}

@Preview(
  uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun EmptyStateScreenDarkPreview() {
  BorrowTheme {
    EmptyStateScreen()
  }
}