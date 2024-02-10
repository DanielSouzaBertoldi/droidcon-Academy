package daniel.bertoldi.bookstorenotes.view

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import daniel.bertoldi.bookstorenotes.BooksApiViewModel
import daniel.bertoldi.bookstorenotes.authorsToString

@Composable
fun BookDetailScreen(
    bookId: String?,
    viewModel: BooksApiViewModel,
    innerPadding: PaddingValues,
) {
    val book = viewModel.getBookDetails(bookId.orEmpty()) // dumb dumb dumb
    val scrollState = rememberScrollState()
    if (book != null) {
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            BookImage(url = book.imageUrl, modifier = Modifier.size(100.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = book.title,
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic,
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = book.authors?.authorsToString() ?: "No Authors!!!!",
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic,
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = book.description ?: "No DESCRIPTION!!!!!",
                fontWeight = FontWeight.Bold,
            )
            Button(onClick = { /*TODO*/ }) {
                Row {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = null)
                    Text(text = "Add To Collection")
                }
            }
        }
    } else {
        Text(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            text = "Oops! Something went terribly wrong!",
        )
    }
}