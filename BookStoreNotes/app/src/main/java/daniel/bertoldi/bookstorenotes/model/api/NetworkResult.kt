package daniel.bertoldi.bookstorenotes.model.api

sealed class NetworkResult<T>( // TODO: maybe there's a way to improve this.
    val data: T? = null,
    val message: String? = null,
) {
    class Initial<T> : NetworkResult<T>()
    class Success<T>(data: T) : NetworkResult<T>(data)
    class Error<T>(data: T? = null, message: String) : NetworkResult<T>(data, message)
    class Loading<T> : NetworkResult<T>()
}