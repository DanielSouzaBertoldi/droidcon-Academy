package com.droidcon.borrow.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.droidcon.borrow.data.dao.BorrowDao
import com.droidcon.borrow.data.models.BorrowItem

@Database(
  entities = [BorrowItem::class],
  version = 1,
  exportSchema = false
)
abstract class BorrowDb : RoomDatabase() {
  abstract fun borrowDao(): BorrowDao
}