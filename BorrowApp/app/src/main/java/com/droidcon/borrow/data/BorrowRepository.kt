package com.droidcon.borrow.data

import com.droidcon.borrow.data.models.BorrowItem
import kotlinx.coroutines.flow.Flow

interface BorrowRepository {
  suspend fun addItem(item: BorrowItem)
  suspend fun getAllBorrowedItems(): Flow<List<BorrowItem>>
}