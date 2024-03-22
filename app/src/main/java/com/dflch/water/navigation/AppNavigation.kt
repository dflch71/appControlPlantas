package com.dflch.water.navigation

import android.os.Build
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dflch.water.LocationViewModel
import com.dflch.water.caItems.ui.viewmodel.ItemViewModel
import com.dflch.water.caPlantillas.ui.viewmodel.PlantillaScreen
import com.dflch.water.caPlantillas.ui.viewmodel.PlantillaViewModel
import com.dflch.water.caPlantillasDet.ui.model.PlantillaDetModel
import com.dflch.water.caPlantillasDet.ui.viewmodel.MuestraScreen
import com.dflch.water.caPlantillasDet.ui.viewmodel.PlantillaDetScreen
import com.dflch.water.caPlantillasDet.ui.viewmodel.PlantillaDetViewModel
import com.dflch.water.caTurnos.ui.viewmodel.TurnoViewModel
import com.dflch.water.caUsers.ui.viewmodel.LoginScreen
import com.dflch.water.caUsers.ui.viewmodel.SplashScreen
import com.dflch.water.caUsers.ui.viewmodel.SplashViewModel
import com.dflch.water.caUsers.ui.viewmodel.UserViewModel
import com.dflch.water.screens.MainScreen
import com.dflch.water.screens.drawer.MenuScreen
import com.dflch.water.screens.drawer.items.ItemDetailScreen
import com.dflch.water.screens.drawer.items.ItemsScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(
    splashViewModel: SplashViewModel,
    userViewModel: UserViewModel,
    itemViewModel: ItemViewModel,
    turnoViewModel: TurnoViewModel,
    plantillaViewModel: PlantillaViewModel,
    plantillaDetViewModel: PlantillaDetViewModel,
    locationViewModel: LocationViewModel
){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.SplashScreen.route
    ) {

        composable(AppScreens.SplashScreen.route) {
            SplashScreen(splashViewModel, userViewModel, itemViewModel, turnoViewModel, plantillaViewModel, plantillaDetViewModel, navController)
        }

        composable(AppScreens.LoginScreen.route) {
            LoginScreen(userViewModel, navController)
        }

        composable(AppScreens.MainScreen.route) {
            //Back Button Disable
            BackHandler(true) {
                // Or do nothing
                Log.i("LOG_TAG", "Clicked back")
            }
            MainScreen(userViewModel, navController)
        }

        composable(AppScreens.MenuScreen.route) {
            //Back Button Disable
            BackHandler(true) {
                // Or do nothing
                Log.i("LOG_TAG", "Clicked back")
            }

            MenuScreen(userViewModel = userViewModel, itemViewModel = itemViewModel)
        }

        composable(AppScreens.TasksScreen.route) {
            ItemsScreen(itemViewModel, navController)
        }

        composable(AppScreens.PlantillaScreen.route) {
            PlantillaScreen(userViewModel, plantillaViewModel, navController)
        }

        composable(AppScreens.PlantillaDetScreen.route) {
            PlantillaDetScreen(userViewModel,  plantillaViewModel, plantillaDetViewModel, turnoViewModel, locationViewModel, navController)
        }

        composable(AppScreens.MuestraScreen.route) {
            BackHandler(true) {
                // Or do nothing
                Log.i("LOG_TAG", "Clicked back")
            }
            MuestraScreen( plantillaViewModel, plantillaDetViewModel, navController)
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