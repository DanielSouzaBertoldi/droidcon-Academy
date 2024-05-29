package com.droidcon.borrow.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.droidcon.borrow.BorrowViewModel
import com.droidcon.borrow.data.models.BorrowItem
import com.droidcon.borrow.navigation.Screens
import com.droidcon.borrow.ui.composables.BorrowedItemRow
import com.droidcon.borrow.ui.composables.EmptyStateScreen
import com.droidcon.borrow.ui.composables.TopBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ListScreen(navController: NavController, borrowViewModel: BorrowViewModel) {
  val keyboardController = LocalSoftwareKeyboardController.current
  keyboardController?.hide()

  borrowViewModel.getAllItems()
  Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = {
      TopBar(title = "Borrowed Items")
    },
    content = {
      val items by borrowViewModel.items.observeAsState()
      if (items.isNullOrEmpty()) {
        EmptyStateScreen()
      } else {
        items?.let {
          ItemList(borrowedItems = it)
        }
      }
    },
    floatingActionButton = {
      ExtendedFloatingActionButton(
        modifier = Modifier
          .padding(16.dp),
        onClick = {
          navController.navigate(Screens.ItemInputScreen.route)
        },
        icon = {
          Icon(
            Icons.Filled.Add,
            contentDescription = "Create"
          )
        },
        text = { Text("Create") }
      )
    }
  )
}

@Composable
fun ItemList(borrowedItems: List<BorrowItem>) {
  LazyColumn(
    modifier = Modifier
      .fillMaxSize()
      .background(Color(0xFFFFFBFE))
      .padding(top = 16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp),
  ) {
    items(borrowedItems) { borrowedItem ->
      BorrowedItemRow(borrowedItem)
    }
  }
}
