package daniel.bertoldi.bookstorenotes.model.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BookService {
    private const val BASE_URL = "https://www.googleapis.com/books/v1/"

    private fun getRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val bookApi: BookApi = getRetrofit().create(BookApi::class.java)
}