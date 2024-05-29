package com.droidcon.borrow.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun ThemeDialog(onPopupDismissed: () -> Unit) {
    Dialog(onDismissRequest = onPopupDismissed) {
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colors.surface, shape = RoundedCornerShape(6.dp))
                .padding(16.dp)
        ) {
            Text(text = "Choose theme", color = MaterialTheme.colors.onSurface)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = false, onCheckedChange = {
                })
                Text(text = "Light", color = MaterialTheme.colors.onSurface)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = false, onCheckedChange = {
                })
                Text(text = "DARK", color = MaterialTheme.colors.onSurface)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = false, onCheckedChange = {
                })
                Text(text = "Follow System", color = MaterialTheme.colors.onSurface)
            }
        }
    }
}