package daniel.bertoldi.bookstorenotes.model.database

import kotlinx.coroutines.flow.Flow

interface CollectionDbRepo {
    suspend fun getBooksFromRepo(): Flow<List<DbBook>>
    suspend fun getBookFromRepo(bookId: String): Flow<DbBook>
    suspend fun addBookToRepo(book: DbBook)
    suspend fun updateBookInRepo(book: DbBook)
    suspend fun deleteBookFromRepo(book: DbBook)
    suspend fun getNotesForBook(bookId: Int): Flow<List<DbNote>>
    suspend fun addNotesToBook(notes: DbNote)
    suspend fun deleteNoteFromBook(note: DbNote)
    suspend fun deleteNotesFromBook(notes: List<DbNote>)
}