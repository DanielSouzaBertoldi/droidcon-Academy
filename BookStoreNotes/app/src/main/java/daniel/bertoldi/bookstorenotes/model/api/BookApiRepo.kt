package daniel.bertoldi.bookstorenotes.model.api // TODO: repo inside the api package? weird

import daniel.bertoldi.bookstorenotes.model.GoogleBooksApiResponse
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookApiRepo(private val api: BookApi) {
    val books = MutableStateFlow<NetworkResult<GoogleBooksApiResponse>>(NetworkResult.Initial())

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
}