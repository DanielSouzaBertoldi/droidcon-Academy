package daniel.bertoldi.bookstorenotes.model.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import daniel.bertoldi.bookstorenotes.model.database.Constants.NOTES_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {
    @Query("SELECT * FROM $NOTES_TABLE WHERE bookId = :bookId ORDER BY noteId ASC")
    fun getNotesForBook(bookId: Int): Flow<List<DbNote>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addNote(note: DbNote)

    @Delete
    fun deleteNotes(note: DbNote)

    @Delete
    fun deleteNotes(note: List<DbNote>)
}