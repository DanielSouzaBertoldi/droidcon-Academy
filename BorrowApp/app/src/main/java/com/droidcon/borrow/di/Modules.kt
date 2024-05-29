package com.droidcon.borrow.di

import androidx.room.Room
import com.droidcon.borrow.BorrowViewModel
import com.droidcon.borrow.data.BorrowDb
import com.droidcon.borrow.data.BorrowRepository
import com.droidcon.borrow.data.BorrowRepositoryImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
  single {
    Room.databaseBuilder(
      androidApplication(),
      BorrowDb::class.java,
      "Borrow.db"
    ).fallbackToDestructiveMigration().build()
  }
}
val repositoryModule = module {
  single<BorrowRepository> { BorrowRepositoryImpl(get()) }
}

val viewModelModule = module {
  viewModel { BorrowViewModel(get()) }
}

val appModules = listOf(
  dataModule,
  repositoryModule,
  viewModelModule
)