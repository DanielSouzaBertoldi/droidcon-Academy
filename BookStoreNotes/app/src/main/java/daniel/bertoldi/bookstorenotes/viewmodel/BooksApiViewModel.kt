package daniel.bertoldi.bookstorenotes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import daniel.bertoldi.bookstorenotes.connectivity.ConnectivityMonitor
import daniel.bertoldi.bookstorenotes.model.api.BookApiRepo
import daniel.bertoldi.bookstorenotes.validateQuery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksApiViewModel @Inject constructor(
    private val repo: BookApiRepo, // TODO: wth, calling repo directly?
    private val connectivityMonitor: ConnectivityMonitor,
) : ViewModel() {

    val result = repo.books
    val queryText = MutableStateFlow("")
    private val queryInput = Channel<String>(Channel.CONFLATED)
    val bookDetails = repo.bookDetails
    val networkAvailable = connectivityMonitor

    init {
        performQuery()
    }

    @OptIn(FlowPreview::class)
    private fun performQuery() {
        viewModelScope.launch(Dispatchers.IO) {
            queryInput.receiveAsFlow()
                .filter { validateQuery(it) }
                .debounce(1000)
                .collect {
                    repo.query(it)
                }
        }
    }

    fun onQueryUpdate(input: String) {
        queryText.value = input
        queryInput.trySend(input)
    }

    fun getSingleBook(id: String) = repo.getSingleBook(id)
}