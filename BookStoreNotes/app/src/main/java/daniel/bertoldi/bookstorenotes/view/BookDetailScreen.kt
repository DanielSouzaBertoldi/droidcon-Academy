package daniel.bertoldi.bookstorenotes.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import daniel.bertoldi.bookstorenotes.viewmodel.BooksApiViewModel
import daniel.bertoldi.bookstorenotes.Destination
import daniel.bertoldi.bookstorenotes.authorsToString
import daniel.bertoldi.bookstorenotes.viewmodel.CollectionDbViewModel

@Composable
fun BookDetailScreen(
    viewModel: BooksApiViewModel,
    collectionDbViewModel: CollectionDbViewModel,
    innerPadding: PaddingValues,
    navController: NavController,
) {
    val book = viewModel.bookDetails.value
    val inCollection by collectionDbViewModel.currentBook.collectAsState()

    if (book == null) { // TODO: nice, no error state at all!
        navController.navigate(Destination.Library.route) {
            popUpTo(Destination.Library.route)
            launchSingleTop = true
        }
    } else {
        LaunchedEffect(Unit) {
            collectionDbViewModel.setCurrentBookId(book.id)
        }

        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            BookImage(url = book.volumeInfo.imageLinks?.thumbnail, modifier = Modifier.size(100.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = book.volumeInfo.title ?: "No Title",
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic,
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = book.volumeInfo.authors?.authorsToString() ?: "No Authors!!!!",
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic,
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = book.volumeInfo.description ?: "No DESCRIPTION!!!!!",
                fontWeight = FontWeight.Bold,
            )
            Button(
                onClick = {
                    if (inCollection == null) {
                        collectionDbViewModel.addBook(book)
                    }
                }
            ) {
                if (inCollection == null) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = null)
                    Text(text = "Add To Collection")
                } else {
                    Icon(imageVector = Icons.Filled.Check, contentDescription = null)
                    Text(text = "Added to Collection")
                }
            }
        }
    }
}