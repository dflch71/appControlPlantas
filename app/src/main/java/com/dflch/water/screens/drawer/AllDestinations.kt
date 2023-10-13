package com.dflch.water.screens.drawer

import androidx.navigation.NavHostController
import com.dflch.water.screens.drawer.AllDestinations.HOME
import com.dflch.water.screens.drawer.AllDestinations.SETTINGS


object AllDestinations {
    const val HOME = "Home"
    const val SETTINGS = "Settings"
}

class AppNavigationActions(private val navController: NavHostController) {

    fun navigateToHome() {
        navController.navigate(HOME) {
            popUpTo(HOME)
        }
    }

    fun navigateToSettings() {
        navController.navigate(SETTINGS) {
            launchSingleTop = true
            restoreState = true
        }
    }
}