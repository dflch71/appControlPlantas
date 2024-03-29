package com.dflch.water.caUsers.ui.viewmodel

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.dflch.water.R
import com.dflch.water.caItems.ui.viewmodel.ItemViewModel
import com.dflch.water.caUsers.ui.UserUiState
import com.dflch.water.navigation.AppScreens
import com.dflch.water.utils.network.ConnectivityObserver
import com.dflch.water.utils.network.NetworkConnectivityObserver
import kotlinx.coroutines.delay
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.ui.draw.scale
import com.dflch.water.caPlantillas.ui.viewmodel.PlantillaViewModel
import com.dflch.water.caPlantillasDet.ui.viewmodel.PlantillaDetViewModel
import com.dflch.water.caTurnos.ui.viewmodel.TurnoViewModel


@Composable
fun SplashScreen(
    splashViewModel: SplashViewModel,
    userViewModel: UserViewModel,
    itemViewModel: ItemViewModel,
    turnoViewModel: TurnoViewModel,
    plantillaViewModel: PlantillaViewModel,
    plantillaDetViewModel: PlantillaDetViewModel,
    navController: NavController
) {

    //val scale = remember {
    //    Animatable(0f)
    //}

    LaunchedEffect(key1 = true) {
        /*
        scale.animateTo(
            targetValue = 0.9f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = {
                    OvershootInterpolator(8f)
                        .getInterpolation(it)
                }
            )
        )
        */

        delay(5000)
        navController.popBackStack() //Tecla Atras deshabilitar
        navController.navigate(AppScreens.LoginScreen.route)
    }

    val isLoading: Boolean by userViewModel.isLoading.observeAsState(initial = false)

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val uiState by produceState<UserUiState>(
        initialValue = UserUiState.Loading,
        key1 = lifecycle,
        key2 = userViewModel
    ) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            userViewModel.uiState.collect { value = it }
        }
    }

    when (uiState) {

        is UserUiState.Error -> {}

        is UserUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is UserUiState.Success -> {
            userViewModel.onCreate()
        }

    }

    //Splash(userViewModel, itemViewModel, scale)
    Splash(userViewModel, itemViewModel, turnoViewModel, plantillaViewModel, plantillaDetViewModel)
}

@Composable
fun Splash(
    userViewModel: UserViewModel,
    itemViewModel: ItemViewModel,
    turnoViewModel: TurnoViewModel,
    plantillaViewModel: PlantillaViewModel,
    plantillaDetViewModel: PlantillaDetViewModel
) {

    val context = LocalContext.current

    lateinit var connectivityObserver: ConnectivityObserver
    connectivityObserver = NetworkConnectivityObserver(context)

    val statusRed by connectivityObserver.observe().collectAsState(
        initial = ConnectivityObserver.Status.Desconectado
    )

    val status: String by userViewModel.status.observeAsState(initial = "Verificando ...")

    val viewModel: ItemViewModel = viewModel { itemViewModel }
    val state by viewModel.stateItem.collectAsState(
        initial = ItemViewModel.UiStateItem(status = "Success")
    )

    val viewModel2: TurnoViewModel = viewModel { turnoViewModel }
    val state2 by viewModel2.stateTurno.collectAsState(
        initial = TurnoViewModel.UiStateTurno(status = "Success")
    )

    val viewModel3: PlantillaViewModel = viewModel { plantillaViewModel }
    val state3 by viewModel3.statePlantilla.collectAsState(
        initial = PlantillaViewModel.UiStatePlantilla(status = "Success")
    )

    val viewModel4: PlantillaDetViewModel = viewModel { plantillaDetViewModel }
    val state4 by viewModel4.statePlantillaDet.collectAsState(
        initial = PlantillaDetViewModel.UiStatePlantillaDet(status = "Success")
    )

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

    ) {

        Image(
            painter = painterResource(id = R.drawable.logoemc),
            contentDescription = "EMC Moviles",
            Modifier.size(200.dp, 200.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "BIENVENID@S EMCARTAGO",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF0585CC)
        )
        Text(
            text = "Estado Red: $statusRed",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF0585CC),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Estado WS: $status",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF0585CC),
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(20.dp))

        CircularProgressIndicator()

        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Cargando Datos WS ...",
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onTertiaryContainer,
            textAlign = TextAlign.Center,
            maxLines = 3
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Item(itemViewModel: ItemViewModel) {

    val viewModel: ItemViewModel = viewModel { itemViewModel }
    val state by viewModel.stateItem.collectAsState()

    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = { TopAppBar(title = { Text(text = "EMCartago") }) }
        ) { padding ->
            if (state.loading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            if (state.items.isNotEmpty()) {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(120.dp),
                    modifier = Modifier.padding(padding),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    contentPadding = PaddingValues(4.dp)
                ) {}

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.azul3),
                        contentDescription = "EMC Moviles",
                        Modifier.size(280.dp, 280.dp)
                    )
                    Text(
                        text = "BIENVENIDO@S EMCARTAGO",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF0585CC)
                    )
                    Text(
                        text = "Estado Red: $state",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF0585CC)
                    )

                    Text(
                        text = "Estado Server: $state",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF0585CC)
                    )
                }
            }
        }
    }
}
