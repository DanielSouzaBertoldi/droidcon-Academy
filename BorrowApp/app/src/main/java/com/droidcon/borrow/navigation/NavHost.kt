package com.droidcon.borrow.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.droidcon.borrow.BorrowViewModel
import com.droidcon.borrow.screens.AddItemScreen
import com.droidcon.borrow.screens.ListScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation(navController: NavHostController, viewModel: BorrowViewModel) {
  AnimatedNavHost(
    navController = navController,
    startDestination = Screens.ListScreen.route,
    enterTransition = { expandIn(animationSpec = tween(800)) },
    exitTransition = { shrinkOut(animationSpec = tween(800)) }
  ) {
    composable(Screens.ListScreen.route,
      enterTransition = {
        if (initialState.destination.route == Screens.ItemInputScreen.route) slideIntoContainer(
          AnimatedContentScope.SlideDirection.Right,
          animationSpec = tween(600)
        )
        else null
      },
      exitTransition = {
        if (targetState.destination.route == Screens.ItemInputScreen.route) slideOutOfContainer(
          AnimatedContentScope.SlideDirection.Left,
          animationSpec = tween(600)
        )
        else null
      }
    ) {
      ListScreen(navController, viewModel)
    }

    composable(Screens.ItemInputScreen.route,
      enterTransition = {
        if (initialState.destination.route == Screens.ListScreen.route) slideIntoContainer(
          AnimatedContentScope.SlideDirection.Left,
          animationSpec = tween(600)
        )
        else null
      },
      exitTransition = {
        if (targetState.destination.route == Screens.ListScreen.route) slideOutOfContainer(
          AnimatedContentScope.SlideDirection.Right,
          animationSpec = tween(600)
        )
        else null
      }
    ) {
      AddItemScreen(navController, viewModel)
    }
  }
}