package daniel.bertoldi.bookstorenotes

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import daniel.bertoldi.bookstorenotes.model.api.BookApiRepo
import daniel.bertoldi.bookstorenotes.model.api.BookService
import daniel.bertoldi.bookstorenotes.model.database.BookDao
import daniel.bertoldi.bookstorenotes.model.database.CollectionDB
import daniel.bertoldi.bookstorenotes.model.database.CollectionDbRepoImpl
import daniel.bertoldi.bookstorenotes.model.database.Constants.DB

@Module
@InstallIn(ViewModelComponent::class)
class HiltModule {
    @Provides
    fun provideApiRepo() = BookApiRepo(BookService.bookApi)

    @Provides
    fun provideBookDb(@ApplicationContext context: Context) = Room
        .databaseBuilder(context, CollectionDB::class.java, DB)
        .build()

    @Provides
    fun provideBookDao(collectionDB: CollectionDB) = collectionDB.bookDao()

    @Provides
    fun provideDbRepo(bookDao: BookDao) = CollectionDbRepoImpl(bookDao) // TODO: ??? maybe there's a better way. All these provides kinda seeem wrong.
}