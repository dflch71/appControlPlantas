package com.dflch.water.navigation

sealed class AppScreens(val route: String) {
    object SplashScreen: AppScreens("splash_screen")
    object LoginScreen:  AppScreens("login_screen")
    object MenuScreen:   AppScreens("menu_screen")
    object TasksScreen:  AppScreens("tasks_screen")
    object ItemDetailScreen:  AppScreens("itemDetail_screen")
}