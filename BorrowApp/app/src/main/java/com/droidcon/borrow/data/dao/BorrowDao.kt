package com.droidcon.borrow.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.droidcon.borrow.data.models.BorrowItem
import kotlinx.coroutines.flow.Flow

@Dao
interface BorrowDao {

  @Insert(onConflict = REPLACE)
  suspend fun addBorrowedItem(item: BorrowItem)

  @Query("SELECT * FROM items")
  fun getAllItems(): Flow<List<BorrowItem>>
}