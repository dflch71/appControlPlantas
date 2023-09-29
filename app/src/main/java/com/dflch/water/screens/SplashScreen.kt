package com.dflch.water.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import com.dflch.water.R
import com.dflch.water.caUsers.ui.UserUiState
import com.dflch.water.caUsers.ui.viewmodel.UserViewModel
import com.dflch.water.navigation.AppScreens
import com.dflch.water.utils.network.ConnectivityObserver
import com.dflch.water.utils.network.NetworkConnectivityObserver
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    userViewModel: UserViewModel,
    navController: NavController
){

    LaunchedEffect(key1 = true){
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
    ){
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED){
            userViewModel.uiState.collect{ value = it }
        }
    }

    when(uiState){

        is UserUiState.Error -> {}

        UserUiState.Loading -> {
            CircularProgressIndicator()
            userViewModel.onCreate()
        }

        is UserUiState.Success -> {
            //if (!isLoading) {}
            userViewModel.onCreate()
        }

    }

    Splash(userViewModel)
}

@Composable
fun Splash(userViewModel: UserViewModel) {

    val context = LocalContext.current
    lateinit var connectivityObserver: ConnectivityObserver
    connectivityObserver = NetworkConnectivityObserver(context)

    val statusRed by connectivityObserver.observe().collectAsState(
        initial = ConnectivityObserver.Status.Desconectado
    )

    val status: String by userViewModel.status.observeAsState(initial = "OK")

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
            text = "Estado Red: $statusRed",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF0585CC)
        )

        Text(
            text = "Estado Server: $status",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF0585CC)
        )
    }

}

