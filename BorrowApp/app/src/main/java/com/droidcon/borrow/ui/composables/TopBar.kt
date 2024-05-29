package com.droidcon.borrow.ui.composables

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TopBar(title: String) {
  TopAppBar(
    title = { Text(title) },
    modifier = Modifier
  )
}