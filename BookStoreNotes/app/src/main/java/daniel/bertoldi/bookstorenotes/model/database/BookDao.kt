package daniel.bertoldi.bookstorenotes.model.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import daniel.bertoldi.bookstorenotes.model.database.Constants.BOOK_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Query("SELECT * FROM $BOOK_TABLE ORDER BY id ASC")
    fun getBooks(): Flow<List<DbBook>>

    @Query("SELECT * FROM $BOOK_TABLE WHERE apiId = :bookId")
    fun getBook(bookId: String): Flow<DbBook>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addBook(book: DbBook)

    @Update
    fun updateBook(book: DbBook)

    @Delete
    fun deleteBook(book: DbBook)
}