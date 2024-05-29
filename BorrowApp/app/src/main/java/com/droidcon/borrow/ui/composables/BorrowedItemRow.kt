package com.droidcon.borrow.ui.composables

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.droidcon.borrow.data.models.BorrowItem
import com.droidcon.borrow.ui.theme.BorrowTheme

@Composable
fun BorrowedItemRow(item: BorrowItem) {

  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp),
    shape = RoundedCornerShape(4.dp),
    backgroundColor = MaterialTheme.colors.surface,
    elevation = 4.dp
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
    ) {
      Text(
        text = item.itemName,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
      )
      Spacer(modifier = Modifier.padding(top = 10.dp))
      Text(text = item.borrowerName)
      Spacer(modifier = Modifier.padding(top = 10.dp))
      Text(text = item.borrowDate)
    }
  }
}

@Preview(group = "BorrowedItemPreview")
@Composable
private fun BorrowedItemRowPreview() {
  BorrowTheme {
    BorrowedItemRow(
      item = BorrowItem(
        id = 0,
        itemName = "PlayStation",
        borrowerName = "Steve",
        borrowDate = "29/05/2024",
      )
    )
  }
}

@Preview(
  uiMode = Configuration.UI_MODE_NIGHT_YES,
  group = "BorrowedItemPreview"
)
@Composable
private fun BorrowedItemRowDarkPreview() {
  BorrowTheme {
    BorrowedItemRow(
      item = BorrowItem(
        id = 0,
        itemName = "PlayStation",
        borrowerName = "Steve",
        borrowDate = "29/05/2024",
      )
    )
  }
}