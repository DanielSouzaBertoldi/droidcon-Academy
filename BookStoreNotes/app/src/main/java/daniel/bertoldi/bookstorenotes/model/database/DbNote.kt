package daniel.bertoldi.bookstorenotes.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import daniel.bertoldi.bookstorenotes.model.database.Constants.NOTES_TABLE

@Entity(tableName = NOTES_TABLE)
data class DbNote(
    @PrimaryKey(autoGenerate = true) val noteId: Int,
    val bookId: Int,
    val title: String,
    val content: String,
) {
    companion object {
        fun fromParameters(bookId: Int, title: String, content: String): DbNote {
            return DbNote(
                noteId = 0,
                bookId = bookId,
                title = title,
                content = content,
            )
        }
    }
}