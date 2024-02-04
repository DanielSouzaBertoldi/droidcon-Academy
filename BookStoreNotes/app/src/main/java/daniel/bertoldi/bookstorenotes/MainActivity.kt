package daniel.bertoldi.bookstorenotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import daniel.bertoldi.bookstorenotes.ui.theme.BookStoreNotesTheme
import daniel.bertoldi.bookstorenotes.view.BookCollectionScreen
import daniel.bertoldi.bookstorenotes.view.BookDetailScreen
import daniel.bertoldi.bookstorenotes.view.BookStoreBottomNav
import daniel.bertoldi.bookstorenotes.view.BookStoreScreen

sealed class Destination(val route: String) {
    object Library: Destination("library")
    object Collection: Destination("collection")
    object BookDetails: Destination("book/{bookId}") {
        fun createRoute(bookId: String?) = "book/$bookId" // TODO: check if there's a better way to do this now
    }
}

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookStoreNotesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val navController = rememberNavController()
                    BookStoreScaffold(navController = navController)
                }
            }
        }
    }
}

@Composable
fun BookStoreScaffold(navController: NavHostController) {
    // val scaffoldState = rememberScaffoldState() <- TODO: doesnt exist anymore, check alternative (maybe remember { SnackbarHostState() })
    Scaffold(
        bottomBar = {
            BookStoreBottomNav(navHostController = navController)
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Destination.Library.route,
        ) {
            composable(Destination.Library.route) {
                BookStoreScreen()
            }
            composable(Destination.Collection.route) {
                BookCollectionScreen()
            }
            composable(Destination.BookDetails.route) {
                BookDetailScreen()
            }
        }
    }
}