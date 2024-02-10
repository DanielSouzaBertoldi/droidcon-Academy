package daniel.bertoldi.bookstorenotes.model.api // TODO: repo inside the api package? weird

import daniel.bertoldi.bookstorenotes.model.GoogleBooksApiResponse
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookApiRepo(private val api: BookApi) { // TODO: Not injecting book api???
    val books = MutableStateFlow<NetworkResult<GoogleBooksApiResponse>>(NetworkResult.Initial())
    // really dumb solution not using Room, keeping everything in memory (not performatic at all!)
    val booksList = mutableListOf<BookDetails>()

    fun query(query: String) {
        books.value = NetworkResult.Loading()
        api.getVolumes(query).enqueue(
            object : Callback<GoogleBooksApiResponse> {
                override fun onResponse(
                    call: Call<GoogleBooksApiResponse>,
                    response: Response<GoogleBooksApiResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            books.value = NetworkResult.Success(it)
                            // dumb dumb dumb
                            it.items.forEach { volume ->
                                if (volume.id != null && volume.volumeInfo.title != null) {
                                    booksList.add(
                                        BookDetails(
                                            id = volume.id,
                                            title = volume.volumeInfo.title,
                                            authors = volume.volumeInfo.authors,
                                            description = volume.volumeInfo.description,
                                            imageUrl = volume.volumeInfo.imageLinks?.thumbnail,
                                        )
                                    )
                                }
                            }
                        }
                    } else {
                        books.value = NetworkResult.Error(message = response.message())
                    }
                }

                override fun onFailure(call: Call<GoogleBooksApiResponse>, t: Throwable) {
                    t.localizedMessage?.let { // TODO: don't really agree with this .let
                        books.value = NetworkResult.Error(message = it)
                    }
                    t.printStackTrace()
                }
            }
        )
    }

    fun getBookDetails(id: String) = booksList.firstOrNull { it.id == id }
}

data class BookDetails(
    val id: String,
    val title: String,
    val authors: List<String>?,
    val description: String?,
    val imageUrl: String?,
)