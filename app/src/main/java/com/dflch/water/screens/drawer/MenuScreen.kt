package com.dflch.water.screens.drawer

import android.graphics.BitmapFactory
import android.util.Base64
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudOff
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.WifiTetheringOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.dflch.water.R
import com.dflch.water.caItems.ui.viewmodel.ItemViewModel
import com.dflch.water.caUsers.ui.viewmodel.UserViewModel
import com.dflch.water.screens.drawer.inicio.HomeScreen
import com.dflch.water.screens.drawer.items.ItemsScreen
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
    userViewModel: UserViewModel,
    itemViewModel: ItemViewModel
) {

    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: AllDestinations.INICIO
    val navigationActions = remember(navController) { AppNavigationActions(navController) }
    val base64: String by userViewModel.base64.observeAsState(initial = "")


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
                        val imageBytes = Base64.decode(base64, Base64.DEFAULT)
                        val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

                        if (status != "Success") {
                            IconButton( onClick = {
                                Toast.makeText(context, "Debe iniciar WEB SERVICE, Consulte al administrador", Toast.LENGTH_SHORT).show()
                            }) {
                                Icon(imageVector = Icons.Filled.CloudOff, contentDescription = "WS")
                            }
                        }

                        if (statusRed != ConnectivityObserver.Status.Conectado) {
                            IconButton(onClick = {
                                Toast.makeText(context, "WI-FI, Datos desconectados", Toast.LENGTH_SHORT).show()
                            }) {
                                Icon(imageVector = Icons.Filled.WifiTetheringOff, contentDescription = "Red")
                            }
                        }

                        /*if (currentRoute == AllDestinations.ITEMS) {
                            IconButton(onClick = {
                                Toast.makeText(context, "Tipos de Datos", Toast.LENGTH_SHORT).show()
                            }) {
                                Icon(imageVector = Icons.Filled.FilterList, contentDescription = "Filter")
                            }

                        }*/

                        AsyncImage(
                            model = ImageRequest
                                .Builder(LocalContext.current)
                                .data(decodedImage)
                                .error(R.drawable.error_user)
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = modifier
                                .size(dimensionResource(id = R.dimen.image_logo))
                                .clip(CircleShape)
                        )

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
                navController = navController, startDestination = AllDestinations.INICIO, modifier = modifier.padding(it)
            ) {

                composable(AllDestinations.INICIO) {
                    HomeScreen()
                }

                composable(AllDestinations.ITEMS) {
                    ItemsScreen(itemViewModel)
                }

            }
        }
    }
}


