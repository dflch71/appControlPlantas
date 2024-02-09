package com.dflch.water

import android.os.Build
import android.os.Bundle
import android.Manifest
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import com.dflch.water.caFoto.ui.viewmodel.FotoViewModel
import com.dflch.water.caItems.ui.viewmodel.ItemViewModel
import com.dflch.water.caPlantillas.ui.viewmodel.PlantillaViewModel
import com.dflch.water.caPlantillasDet.ui.viewmodel.PlantillaDetViewModel
import com.dflch.water.caTurnos.ui.viewmodel.TurnoViewModel
import com.dflch.water.caUsers.ui.viewmodel.SplashViewModel
import com.dflch.water.caUsers.ui.viewmodel.UserViewModel
import com.dflch.water.navigation.AppNavigation
import com.dflch.water.ui.theme.WaterTheme
import com.dflch.water.utils.LocationManager
import dagger.hilt.android.AndroidEntryPoint


class LocationViewModel: ViewModel() {
    val altitud = mutableStateOf(0.0)
    val latitud = mutableStateOf(0.0)
    val longitud = mutableStateOf(0.0)
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val itemViewModel: ItemViewModel by viewModels()
    private val userViewModel: UserViewModel by viewModels()
    private val splashViewModel: SplashViewModel by viewModels()
    private val fotoViewModel: FotoViewModel by viewModels()
    private val turnoViewModel: TurnoViewModel by viewModels()
    private val plantillaViewModel: PlantillaViewModel by viewModels()
    private val plantillaDetViewModel: PlantillaDetViewModel by viewModels()

    //GPS position
    private val viewModel: LocationViewModel by viewModels()
    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permisions ->
        when {
            permisions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {

                //this is the fine location
                LocationManager.Builder
                    .create(this@MainActivity)
                    .request( onUpdateLocation =  { altitud: Double, latitud: Double , longitud: Double ->
                       // request one time, should remove the callback
                        LocationManager.removeCallback(this@MainActivity)
                        viewModel.altitud.value = altitud
                        viewModel.latitud.value = latitud
                        viewModel.longitud.value = longitud
                    })
            }

            permisions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {

                //if you need, request here again for coarse location
                LocationManager.Builder
                    .create(this@MainActivity)
                    .request( onUpdateLocation =  { altitud: Double, latitud: Double , longitud: Double ->
                        LocationManager.removeCallback(this@MainActivity)
                        viewModel.altitud.value = altitud
                        viewModel.latitud.value = latitud
                        viewModel.longitud.value = longitud
                    })
            }

            else -> {
                // go setting screen
                LocationManager.goSettingScreen(this@MainActivity)
            }

        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WaterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    AppNavigation(
                        splashViewModel,
                        userViewModel,
                        itemViewModel,
                        turnoViewModel,
                        plantillaViewModel,
                        plantillaDetViewModel,
                        viewModel //locationViewModel
                    )
                }
            }
        }
    }


    override fun onResume() {
        super.onResume()
        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }
}

