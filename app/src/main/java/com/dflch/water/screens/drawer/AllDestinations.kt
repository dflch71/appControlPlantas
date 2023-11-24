package com.dflch.water.screens.drawer

import androidx.navigation.NavHostController
import com.dflch.water.screens.drawer.AllDestinations.INICIO
import com.dflch.water.screens.drawer.AllDestinations.ITEMS
import com.dflch.water.screens.drawer.AllDestinations.ITEM_DETAIL


object AllDestinations {
    const val INICIO = "Inicio"
    const val ITEMS = "Items"
    const val ITEM_DETAIL = "ItemDetail"
}

class AppNavigationActions(private val navController: NavHostController) {

    fun navigateToHome() {
        navController.navigate(INICIO) {
            popUpTo(INICIO)
        }
    }

    fun navigateToSettings() {
        navController.navigate(ITEMS) {
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateToItemDetail() {
        navController.navigate(ITEM_DETAIL) {
            launchSingleTop = true
            restoreState = true
        }
    }
}