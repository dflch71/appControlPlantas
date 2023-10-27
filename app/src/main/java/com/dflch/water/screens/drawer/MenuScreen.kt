package com.dflch.water.screens.drawer

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudOff
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.NetworkWifi
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.WifiTetheringOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dflch.water.caFoto.ui.viewmodel.FotoViewModel
import com.dflch.water.caUsers.ui.UserUiState
import com.dflch.water.caUsers.ui.viewmodel.UserViewModel
import com.dflch.water.screens.drawer.home.HomeScreen
import com.dflch.water.screens.drawer.setting.SettingsScreen
import com.dflch.water.screens.template.AlertDialogError
import com.dflch.water.utils.network.ConnectivityObserver
import com.dflch.water.utils.network.NetworkConnectivityObserver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    userViewModel: UserViewModel
) {

    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: AllDestinations.HOME
    val navigationActions = remember(navController) { AppNavigationActions(navController) }


    ModalNavigationDrawer(drawerContent = {
        AppDrawer(
            route = currentRoute,
            navigateToHome = { navigationActions.navigateToHome() },
            navigateToSettings = { navigationActions.navigateToSettings() },
            closeDrawer = { coroutineScope.launch { drawerState.close() } },
            modifier = Modifier,
            userViewModel = userViewModel
        )
    }, drawerState = drawerState) {

        val context = LocalContext.current
        lateinit var connectivityObserver: ConnectivityObserver
        connectivityObserver = NetworkConnectivityObserver(context)
        val statusRed by connectivityObserver.observe().collectAsState(
            initial = ConnectivityObserver.Status.Desconectado
        )

        val status: String by userViewModel.status.observeAsState(initial = "Success ...")



        Scaffold(
            topBar = {
                TopAppBar (title = { Text(text = currentRoute) },
                    actions = {

                        if (status != "Success") {
                            IconButton( onClick = {
                                Toast.makeText(context, "Debe iniciar WEB SERVICE, Consulte al administrador", Toast.LENGTH_SHORT).show()
                            }) {
                                Icon(imageVector = Icons.Filled.CloudOff, contentDescription = "WS")
                            }
                        }

                        if (statusRed != ConnectivityObserver.Status.Conectado) {
                            IconButton(onClick = { }) {
                                Icon(imageVector = Icons.Filled.WifiTetheringOff, contentDescription = "Red")
                            }
                        }

                    },
                    modifier = Modifier.fillMaxWidth(),
                    navigationIcon = { IconButton(onClick = {
                        coroutineScope.launch { drawerState.open() }
                    }, content = {
                        Icon(
                            imageVector = Icons.Default.Menu, contentDescription = null
                        )
                    })
                    }, colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer))
            }, modifier = Modifier
        ) {
            NavHost(
                navController = navController, startDestination = AllDestinations.HOME, modifier = modifier.padding(it)
            ) {

                composable(AllDestinations.HOME) {
                    HomeScreen()
                }

                composable(AllDestinations.SETTINGS) {
                    SettingsScreen()
                }
            }
        }
    }
}


