package com.droidcon.borrow.screens

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.droidcon.borrow.BorrowViewModel
import com.droidcon.borrow.ui.composables.BackTopBar
import com.droidcon.borrow.ui.composables.DatePicker
import com.droidcon.borrow.ui.composables.InputText
import com.droidcon.borrow.ui.composables.SaveButton

@Composable
fun AddItemScreen(navController: NavController, borrowViewModel: BorrowViewModel) {
  Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = {
      BackTopBar(title = "Add Borrowed Item") {
        navController.popBackStack()
      }
    },
    content = {
      AddItemForm(borrowViewModel, navController, it)
    }
  )
}

@Composable
fun AddItemForm(borrowViewModel: BorrowViewModel, navController: NavController, paddingValues: PaddingValues) {
  val activity = LocalContext.current as ComponentActivity
  var dateSelected by remember { mutableStateOf("") }
  val updateDate = { date: String? ->
    dateSelected = date ?: ""
  }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(color = Color(0xFFFFFBFE))
      .padding(paddingValues)
  ) {
    InputText(
      label = "Enter item name",
      requestFocus = true,
      onTextChange = { borrowViewModel.itemName = it })
    InputText(
      label = "Enter borrower name",
      onTextChange = { borrowViewModel.borrowerName = it }
    )
    DatePicker(datePicked = dateSelected, updatedDate = updateDate, activity = activity , viewModel = borrowViewModel)
    SaveButton(viewModel = borrowViewModel, navController = navController)
  }
}