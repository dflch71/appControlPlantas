package com.dflch.water.screens.drawer

import androidx.navigation.NavHostController
import com.dflch.water.screens.drawer.AllDestinations.INICIO
import com.dflch.water.screens.drawer.AllDestinations.ITEMS


object AllDestinations {
    const val INICIO = "Inicio"
    const val ITEMS = "Items"
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

}