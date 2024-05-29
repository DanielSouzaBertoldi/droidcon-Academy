package com.droidcon.borrow.navigation

sealed class Screens(val route: String) {
  object ListScreen : Screens("listScreen")
  object ItemInputScreen : Screens("itemInputScreen")
}