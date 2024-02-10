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
import androidx.navigation.NavController
import coil.compose.AsyncImage
import daniel.bertoldi.bookstorenotes.BooksApiViewModel
import daniel.bertoldi.bookstorenotes.Destination
import daniel.bertoldi.bookstorenotes.authorsToString

@Composable
fun BookDetailScreen(
    viewModel: BooksApiViewModel,
    innerPadding: PaddingValues,
    navController: NavController,
) {
    val book = viewModel.bookDetails.value

    if (book == null) { // TODO: nice, no error state at all!
        navController.navigate(Destination.Library.route) {
            popUpTo(Destination.Library.route)
            launchSingleTop = true
        }
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
        BookImage(url = book?.volumeInfo?.imageLinks?.thumbnail, modifier = Modifier.size(100.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = book?.volumeInfo?.title ?: "No Title",
            textAlign = TextAlign.Center,
            fontStyle = FontStyle.Italic,
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = book?.volumeInfo?.authors?.authorsToString() ?: "No Authors!!!!",
            textAlign = TextAlign.Center,
            fontStyle = FontStyle.Italic,
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = book?.volumeInfo?.description ?: "No DESCRIPTION!!!!!",
            fontWeight = FontWeight.Bold,
        )
        Button(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = null)
            Text(text = "Add To Collection")
        }
    }
}