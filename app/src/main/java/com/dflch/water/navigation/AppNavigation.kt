package com.dflch.water.navigation

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dflch.water.caItems.ui.viewmodel.ItemViewModel
import com.dflch.water.caUsers.ui.viewmodel.LoginScreen
import com.dflch.water.caUsers.ui.viewmodel.SplashScreen
import com.dflch.water.caUsers.ui.viewmodel.SplashViewModel
import com.dflch.water.caUsers.ui.viewmodel.UserViewModel
import com.dflch.water.screens.drawer.MenuScreen
import com.dflch.water.screens.drawer.items.ItemDetailScreen
import com.dflch.water.screens.drawer.items.ItemsScreen

@Composable
fun AppNavigation(
    splashViewModel: SplashViewModel,
    userViewModel: UserViewModel,
    itemViewModel: ItemViewModel
){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.SplashScreen.route
    ) {


        composable(AppScreens.SplashScreen.route) {
            SplashScreen(splashViewModel, userViewModel, itemViewModel, navController)
        }

        composable(AppScreens.LoginScreen.route) {
            LoginScreen(userViewModel, navController)
        }

        composable(AppScreens.MenuScreen.route) {
            //Back Button Disable
            BackHandler(true) {
                // Or do nothing
                Log.i("LOG_TAG", "Clicked back")
            }

            //MainScreen(navController)
            MenuScreen(userViewModel = userViewModel, itemViewModel = itemViewModel)
        }

        composable(AppScreens.TasksScreen.route) {
            ItemsScreen(itemViewModel, navController)
        }

        composable(
            AppScreens.ItemDetailScreen.route + "/{itemId}",
            arguments = listOf(
                navArgument("itemId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt("itemId")
            requireNotNull(itemId)
            ItemDetailScreen(itemId)
        }

    }
}