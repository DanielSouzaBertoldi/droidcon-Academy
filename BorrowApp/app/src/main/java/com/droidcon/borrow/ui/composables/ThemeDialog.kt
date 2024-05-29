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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.droidcon.borrow.ui.theme.LocalTheme
import com.droidcon.borrow.ui.theme.Theme

@Composable
fun ThemeDialog(onPopupDismissed: () -> Unit) {
    var theme by LocalTheme.current
    Dialog(onDismissRequest = onPopupDismissed) {
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colors.surface, shape = RoundedCornerShape(6.dp))
                .padding(16.dp)
        ) {
            Text(text = "Choose theme", color = MaterialTheme.colors.onSurface)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = theme == Theme.LIGHT, onCheckedChange = {
                    theme = Theme.LIGHT
                })
                Text(text = "Light", color = MaterialTheme.colors.onSurface)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = theme == Theme.DARK, onCheckedChange = {
                    theme = Theme.DARK
                })
                Text(text = "DARK", color = MaterialTheme.colors.onSurface)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = theme == Theme.FOLLOW_SYSTEM, onCheckedChange = {
                    theme = Theme.FOLLOW_SYSTEM
                })
                Text(text = "Follow System", color = MaterialTheme.colors.onSurface)
            }
        }
    }
}