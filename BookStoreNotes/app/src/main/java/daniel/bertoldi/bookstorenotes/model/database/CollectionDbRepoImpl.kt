package daniel.bertoldi.bookstorenotes.model.database

class CollectionDbRepoImpl(
    private val bookDao: BookDao,
    private val notesDao: NotesDao,
) : CollectionDbRepo {
    override suspend fun getBooksFromRepo() = bookDao.getBooks()
    override suspend fun getBookFromRepo(bookId: String) = bookDao.getBook(bookId)
    override suspend fun addBookToRepo(book: DbBook) = bookDao.addBook(book)
    override suspend fun updateBookInRepo(book: DbBook) = bookDao.updateBook(book)
    override suspend fun deleteBookFromRepo(book: DbBook) = bookDao.deleteBook(book)
    override suspend fun getNotesForBook(bookId: Int) = notesDao.getNotesForBook(bookId)
    override suspend fun addNotesToBook(notes: DbNote) = notesDao.addNote(notes)
    override suspend fun deleteNoteFromBook(note: DbNote) = notesDao.deleteNotes(note)
    override suspend fun deleteNotesFromBook(notes: List<DbNote>) = notesDao.deleteNotes(notes)
}