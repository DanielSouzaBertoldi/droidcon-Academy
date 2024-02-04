package daniel.bertoldi.bookstorenotes

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import daniel.bertoldi.bookstorenotes.ui.theme.BookStoreNotesTheme

sealed class Destination(val route: String) {
    object Library: Destination("library")
    object Collection: Destination("collection")
    object BookDetails: Destination("book/{bookId}") {
        fun createRoute(bookId: String?) = "book/$bookId" // check if there's a better way to do this now
    }
}

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContent {
            BookStoreNotesTheme {

            }
        }
    }
}