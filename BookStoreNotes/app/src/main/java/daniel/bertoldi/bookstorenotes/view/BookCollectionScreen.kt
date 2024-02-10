package daniel.bertoldi.bookstorenotes.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import daniel.bertoldi.bookstorenotes.model.database.DbNote
import daniel.bertoldi.bookstorenotes.viewmodel.CollectionDbViewModel

@Composable
fun BookCollectionScreen(cvm: CollectionDbViewModel) {
    val booksInCollection = cvm.collection.collectAsState()
    val notesForBook = cvm.bookNotes.collectAsState()

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(booksInCollection.value) { book ->
            var isNotesSectionExpanded by rememberSaveable {
                mutableStateOf(false)
            }
            var isAddingNote by rememberSaveable {
                mutableStateOf(false)
            }
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(4.dp)
                        .clickable {
                            cvm.getNotes(book.id)
                            isNotesSectionExpanded = !isNotesSectionExpanded
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BookImage(
                        url = book.thumbnail,
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxHeight(),
                        contentScale = ContentScale.FillHeight
                    )

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp)
                            .fillMaxHeight()
                    ) {
                        Text(
                            text = book.title ?: "No title",
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp,
                            maxLines = 2
                        )
                        Text(
                            text = book.authors ?: "",
                            fontStyle = FontStyle.Italic,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }

                    Column(
                        modifier = Modifier
                            .wrapContentWidth()
                            .fillMaxHeight()
                            .padding(4.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            Icons.Outlined.Delete,
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                cvm.deleteBook(book)
                                cvm.deleteNotes(notesForBook.value)
                            }
                        )

                        val rotation = animateFloatAsState(
                            targetValue = if (isNotesSectionExpanded) 180F else 0F,
                            label = "arrowRotation"
                        )
                        Icon(
                            modifier = Modifier.rotate(rotation.value),
                            imageVector = Icons.Outlined.KeyboardArrowDown,
                            contentDescription = null,
                        )
                    }
                }

                AnimatedVisibility(
                    visible = isNotesSectionExpanded,
                    enter = expandVertically(),
                    exit = shrinkVertically(),
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                        ) {
                            notesForBook.value.forEach {
                                Row(
                                    modifier = Modifier
                                        .background(MaterialTheme.colorScheme.inversePrimary),
                                    verticalAlignment = Alignment.Top,
                                ) {
                                    Column(modifier = Modifier.weight(0.8f)) {
                                        Text(text = it.title, fontWeight = FontWeight.Bold)
                                        Text(text = it.content, fontStyle = FontStyle.Italic)
                                    }
                                    IconButton(
                                        modifier = Modifier.weight(0.2f),
                                        onClick = { cvm.deleteNote(it) }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Outlined.Delete,
                                            contentDescription = null,
                                        )
                                    }
                                }
                            }
                        }
                        AnimatedVisibility(visible = isAddingNote) {
                            val title = remember { mutableStateOf("") }
                            val content = remember { mutableStateOf("") }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(MaterialTheme.colorScheme.background),
                                verticalAlignment = Alignment.Bottom,
                            ) {
                                Column(modifier = Modifier.weight(0.7f)) {
                                    Text(text = "Add note")
                                    OutlinedTextField(
                                        value = title.value,
                                        onValueChange = { title.value = it }
                                    )
                                    OutlinedTextField(
                                        value = content.value,
                                        onValueChange = { content.value = it }
                                    )
                                }
                                IconButton(
                                    modifier = Modifier
                                        .weight(0.3f)
                                        .background(
                                            color = MaterialTheme.colorScheme.primary,
                                            shape = RoundedCornerShape(10.dp)
                                        ),
                                    onClick = {
                                        cvm.addNote(
                                            DbNote.fromParameters(book.id, title.value, content.value)
                                        )
                                        isAddingNote = false
                                    }
                                ) {
                                    Icon(Icons.Outlined.Check, contentDescription = null)
                                }
                            }
                        }
                        IconButton(
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .background(
                                    color = MaterialTheme.colorScheme.primary,
                                    shape = RoundedCornerShape(10.dp)
                                ),
                            onClick = { isAddingNote = true },
                        ) {
                            Icon(imageVector = Icons.Outlined.Add, contentDescription = null)
                        }
                    }
                }

                HorizontalDivider(
                    modifier = Modifier.padding(
                        top = 4.dp,
                        bottom = 4.dp,
                        start = 20.dp,
                        end = 20.dp
                    ),
                    color = Color.LightGray
                )
            }
        }
    }
}