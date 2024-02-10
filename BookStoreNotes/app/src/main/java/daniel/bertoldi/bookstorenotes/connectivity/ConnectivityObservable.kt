package daniel.bertoldi.bookstorenotes.connectivity

import kotlinx.coroutines.flow.Flow

interface  ConnectivityObservable {
    enum class Status{
        AVAILABLE,
        UNAVAILABLE
    }

    fun observe(): Flow<Status>
}