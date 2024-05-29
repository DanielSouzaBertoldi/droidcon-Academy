package com.droidcon.borrow.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.droidcon.borrow.R

@Composable
fun TopBar(title: String) {
    TopAppBar(
        title = { Text(title) },
        modifier = Modifier,
        actions = {
            var showPopup by remember { mutableStateOf(false) }

            Icon(
                painter = painterResource(id = R.drawable.ic_dark_mode),
                contentDescription = "Toggle Theme",
                modifier = Modifier.clickable { showPopup = true },
            )

            val onPopupDismissed = { showPopup = false }
            if (showPopup) {
                ThemeDialog(onPopupDismissed)
            }
        }
    )
}