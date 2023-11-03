package com.dflch.water

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dflch.water.caFoto.ui.viewmodel.FotoViewModel
import com.dflch.water.caItems.ui.viewmodel.ItemViewModel
import com.dflch.water.caUsers.ui.viewmodel.LoginScreen
import com.dflch.water.caUsers.ui.viewmodel.SplashScreen
import com.dflch.water.caUsers.ui.viewmodel.SplashViewModel
import com.dflch.water.caUsers.ui.viewmodel.UserViewModel
import com.dflch.water.navigation.AppScreens
import com.dflch.water.screens.drawer.MenuScreen
import com.dflch.water.ui.theme.WaterTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val itemViewModel: ItemViewModel by viewModels()
    private val userViewModel: UserViewModel by viewModels()
    private val splashViewModel: SplashViewModel by viewModels()
    private val fotoViewModel: FotoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WaterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigationMain()
                    //TasksScreen(tasksViewModel)
                    //TasksScreen()
                }
            }
        }
    }

    @Composable
    fun AppNavigationMain(){

        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = AppScreens.SplashScreen.route
            //startDestination = AppScreens.MenuScreen.route
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
                //
                //
                //TasksScreen(tasksViewModel)
            }
        }
    }
}

