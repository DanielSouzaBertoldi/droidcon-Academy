package daniel.bertoldi.bookstorenotes

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import daniel.bertoldi.bookstorenotes.connectivity.ConnectivityMonitor
import daniel.bertoldi.bookstorenotes.model.api.BookApiRepo
import daniel.bertoldi.bookstorenotes.model.api.BookService
import daniel.bertoldi.bookstorenotes.model.database.BookDao
import daniel.bertoldi.bookstorenotes.model.database.CollectionDB
import daniel.bertoldi.bookstorenotes.model.database.CollectionDbRepo
import daniel.bertoldi.bookstorenotes.model.database.CollectionDbRepoImpl
import daniel.bertoldi.bookstorenotes.model.database.Constants.DB
import daniel.bertoldi.bookstorenotes.model.database.NotesDao

@Module
@InstallIn(ViewModelComponent::class)
class HiltModule {
    // TODO: ??? maybe there's a better way. All these provides kinda seeem wrong.
    @Provides
    fun provideApiRepo() = BookApiRepo(BookService.bookApi)

    @Provides
    fun provideBookDb(@ApplicationContext context: Context) = Room
        .databaseBuilder(context, CollectionDB::class.java, DB)
        .build()

    @Provides
    fun provideBookDao(collectionDB: CollectionDB) = collectionDB.bookDao()

    @Provides
    fun provideNotesDao(collectionDB: CollectionDB) = collectionDB.notesDao()

    @Provides
    fun provideDbRepo(
        bookDao: BookDao,
        notesDao: NotesDao,
    ): CollectionDbRepo = CollectionDbRepoImpl(bookDao, notesDao)

    @Provides
    fun provideConnectivityMonitor(@ApplicationContext context: Context) =
        ConnectivityMonitor.getInstance(context)
}