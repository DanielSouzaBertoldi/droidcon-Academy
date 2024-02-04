package daniel.bertoldi.bookstorenotes.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.automirrored.filled.MenuOpen
import androidx.compose.material.icons.filled.Book
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import daniel.bertoldi.bookstorenotes.Destination

@Composable
fun BookStoreBottomNav(navHostController: NavHostController) {
    BottomAppBar(
        tonalElevation = 5.dp,
    ) {
        val navBackStackEntry = navHostController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry.value?.destination

        NavigationBarItem(
            selected = currentDestination?.route == Destination.Library.route,
            onClick = {
                navHostController.navigate(Destination.Library.route) {
                    popUpTo(Destination.Library.route)
                    launchSingleTop = true
                }
            },
            icon = { 
                Icon(imageVector = Icons.AutoMirrored.Filled.MenuBook, contentDescription = null)
            },
            label = { Text(text = Destination.Library.route) }
        )

        NavigationBarItem(
            selected = currentDestination?.route == Destination.Collection.route,
            onClick = {
                navHostController.navigate(Destination.Collection.route) {
                    popUpTo(Destination.Collection.route)
                    launchSingleTop = true
                }
            },
            icon = {
                Icon(imageVector = Icons.Filled.Book, contentDescription = null)
            },
            label = { Text(text = Destination.Collection.route) }
        )
    }
}