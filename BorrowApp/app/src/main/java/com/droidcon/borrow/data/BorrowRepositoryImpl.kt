package com.droidcon.borrow.data

import com.droidcon.borrow.data.models.BorrowItem
import kotlinx.coroutines.flow.Flow

class BorrowRepositoryImpl(private val borrowDb: BorrowDb): BorrowRepository {
  override suspend fun addItem(item: BorrowItem) {
    borrowDb.borrowDao().addBorrowedItem(item)
  }

  override suspend fun getAllBorrowedItems(): Flow<List<BorrowItem>> {
    return borrowDb.borrowDao().getAllItems()
  }
}