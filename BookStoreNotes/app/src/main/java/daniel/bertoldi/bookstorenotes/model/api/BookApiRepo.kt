package daniel.bertoldi.bookstorenotes.model.api // TODO: repo inside the api package? weird

import androidx.compose.runtime.mutableStateOf
import daniel.bertoldi.bookstorenotes.model.GoogleBooksApiResponse
import daniel.bertoldi.bookstorenotes.model.Volume
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookApiRepo(private val api: BookApi) { // TODO: Not injecting book api???
    val books = MutableStateFlow<NetworkResult<GoogleBooksApiResponse>>(NetworkResult.Initial())
    val bookDetails = mutableStateOf<Volume?>(null)

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
                            books.value = NetworkResult.Success(it) // TODO: WHY NOT MAP THE RESPONSE TO A NEW MODEL?!!!
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

    fun getSingleBook(id: String) {
        bookDetails.value = books.value.data?.items?.firstOrNull { it.id == id }
    }
}

data class BookDetails(
    val id: String,
    val title: String,
    val authors: List<String>?,
    val description: String?,
    val imageUrl: String?,
)