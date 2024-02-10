package daniel.bertoldi.bookstorenotes

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import daniel.bertoldi.bookstorenotes.ui.theme.BookStoreNotesTheme
import daniel.bertoldi.bookstorenotes.view.BookCollectionScreen
import daniel.bertoldi.bookstorenotes.view.BookDetailScreen
import daniel.bertoldi.bookstorenotes.view.BookStoreBottomNav
import daniel.bertoldi.bookstorenotes.view.BookstoreScreen
import daniel.bertoldi.bookstorenotes.viewmodel.BooksApiViewModel
import daniel.bertoldi.bookstorenotes.viewmodel.CollectionDbViewModel

sealed class Destination(val route: String) {
    data object Library: Destination("library")
    data object Collection: Destination("collection")
    data object BookDetails: Destination("book/{bookId}") {
        fun createRoute(bookId: String?) = "book/$bookId" // TODO: check if there's a better way to do this now
    }
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val booksViewModel by viewModels<BooksApiViewModel>()
    private val collectionViewModel by viewModels<CollectionDbViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookStoreNotesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val navController = rememberNavController()
                    BookStoreScaffold(
                        navController = navController,
                        viewModel = booksViewModel,
                        collectionViewModel = collectionViewModel,
                    )
                }
            }
        }
    }
}

@Composable
fun BookStoreScaffold(
    navController: NavHostController,
    viewModel: BooksApiViewModel,
    collectionViewModel: CollectionDbViewModel,
) {
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
                BookstoreScreen(
                    navController = navController,
                    vm = viewModel,
                    paddingValues = innerPadding,
                )
            }
            composable(Destination.Collection.route) {
                BookCollectionScreen(collectionViewModel)
            }
            composable(Destination.BookDetails.route) {
                val id = it.arguments?.getString("bookId")
                if (id == null) {
                    Toast.makeText(LocalContext.current, "Book id is required", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.getSingleBook(id)
                    BookDetailScreen(
                        viewModel = viewModel,
                        innerPadding = innerPadding,
                        navController = navController,
                    )
                }
            }
        }
    }
}