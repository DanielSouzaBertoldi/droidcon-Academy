package daniel.bertoldi.bookstorenotes.model.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        DbBook::class,
        DbNote::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class CollectionDB : RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun notesDao(): NotesDao
}