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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dflch.water.caUsers.ui.LoginScreen
import com.dflch.water.caUsers.ui.viewmodel.UserViewModel
import com.dflch.water.navigation.AppScreens
import com.dflch.water.screens.MainScreen
import com.dflch.water.screens.SplashScreen
import com.dflch.water.ui.theme.WaterTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val userViewModel: UserViewModel by viewModels()

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
        ) {
            composable(AppScreens.SplashScreen.route) {
                SplashScreen(userViewModel, navController)
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

                MainScreen(navController)
            }

            composable(AppScreens.TasksScreen.route) {
                //
                //
                // TasksScreen(tasksViewModel)
            }
        }
    }
}

