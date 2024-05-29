package com.droidcon.borrow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidcon.borrow.data.BorrowRepository
import com.droidcon.borrow.data.models.BorrowItem
import kotlinx.coroutines.launch

class BorrowViewModel(private val borrowRepository: BorrowRepository): ViewModel() {

  private val _items = MutableLiveData<List<BorrowItem>>()
  val items: LiveData<List<BorrowItem>> = _items

  var itemName = ""
  var borrowerName = ""
  var date = ""

  fun addItem() {
    viewModelScope.launch {
      borrowRepository.addItem(
        BorrowItem(
          id = 0,
          itemName = itemName,
          borrowerName = borrowerName,
          borrowDate = date
        )
      )
    }
  }

  fun getAllItems() {
    viewModelScope.launch {
      borrowRepository.getAllBorrowedItems().collect { items ->
        _items.postValue(items)
      }
    }
  }

  fun validateInputs(): Boolean {
    return itemName.isNotEmpty() && borrowerName.isNotEmpty() && date.isNotEmpty()
  }
}