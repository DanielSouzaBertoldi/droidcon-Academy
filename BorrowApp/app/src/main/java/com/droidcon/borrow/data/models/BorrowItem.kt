package com.droidcon.borrow.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "items")
data class BorrowItem(
  @PrimaryKey(autoGenerate = true)
  var id: Long = 0,
  val itemName: String,
  val borrowerName: String,
  val borrowDate: String
)