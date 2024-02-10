package daniel.bertoldi.bookstorenotes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import daniel.bertoldi.bookstorenotes.model.Volume
import daniel.bertoldi.bookstorenotes.model.database.CollectionDbRepo
import daniel.bertoldi.bookstorenotes.model.database.DbBook
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionDbViewModel @Inject constructor(  // TODO: why different viewmodels for remote/local sources? A proper repository could easily fix this.
    private val repo: CollectionDbRepo,
): ViewModel() {
    val collection = MutableStateFlow<List<DbBook>>(emptyList())
    val currentBook = MutableStateFlow<DbBook?>(null)

    init {
        getCollection()
    }

    private fun getCollection() {
        viewModelScope.launch {
            repo.getBooksFromRepo().collect {
                collection.value = it
            }
        }
    }

    fun setCurrentBookId(bookId: String?) {
        bookId?.let {
            viewModelScope.launch {
                repo.getBookFromRepo(it).collect {
                    currentBook.value = it
                }
            }
        }
    }

    fun addBook(book: Volume) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.addBookToRepo(DbBook.fromVolume(book))
        }
    }

    fun deleteBook(book: DbBook) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteBookFromRepo(book)
        }
    }
}