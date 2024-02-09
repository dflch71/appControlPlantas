package com.dflch.water.caPlantillasDet.ui.viewmodel

import android.graphics.BitmapFactory
import android.os.Build
import android.util.Base64
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.dflch.water.LocationViewModel
import com.dflch.water.MainActivity
import com.dflch.water.R
import com.dflch.water.caPlantillas.ui.viewmodel.PlantillaViewModel
import com.dflch.water.caPlantillasDet.data.model.LugaresMuestra
import com.dflch.water.caPlantillasDet.ui.model.LugaresMuestraModel
import com.dflch.water.caPlantillasDet.ui.model.PlantillaDetModel
import com.dflch.water.caTurnos.ui.viewmodel.TurnoViewModel
import com.dflch.water.caUsers.ui.viewmodel.UserViewModel
import com.dflch.water.utils.Constants.currentDateTime
import com.dflch.water.utils.Constants.floatFormat
import com.dflch.water.utils.LocationManager


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PlantillaDetScreen (
    userViewModel: UserViewModel,
    plantillaViewModel: PlantillaViewModel,
    plantillaDetViewModel: PlantillaDetViewModel,
    turnoViewModel: TurnoViewModel,
    locationViewModel: LocationViewModel,
    navController: NavController
) {

    BodyContentMain(
        userViewModel = userViewModel,
        plantillaViewModel = plantillaViewModel,
        plantillaDetViewModel = plantillaDetViewModel,
        turnoViewModel = turnoViewModel,
        locationViewModel = locationViewModel,
        navController = navController,
        modifier = Modifier
    )

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BodyContentMain(
    userViewModel: UserViewModel,
    plantillaViewModel: PlantillaViewModel,
    plantillaDetViewModel: PlantillaDetViewModel,
    turnoViewModel: TurnoViewModel,
    locationViewModel: LocationViewModel,
    navController: NavController,
    modifier: Modifier
) {

    //userViewModel
    val idUser: String by userViewModel.idUser.observeAsState(initial = "")
    val nombre:String by userViewModel.nombre.observeAsState(initial = "Nombres")
    val apellido:String by userViewModel.apellido.observeAsState(initial = "Apellidos")
    val base64: String by userViewModel.base64.observeAsState(initial = "")

    //plantillaViewModel
    val idPlantilla: String by plantillaViewModel.idPlantilla.observeAsState(initial = "")
    val namePlantilla: String by plantillaViewModel.namePlantilla.observeAsState(initial = "")
    val nameLugar: String by plantillaViewModel.nameLugar.observeAsState(initial = "")

    //turnoViewModel
    val turnoActivo: String by turnoViewModel.turnoActivo.observeAsState(initial = "")

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(MaterialTheme.colorScheme.primary)
            //.clip(MaterialTheme.shapes.extraLarge)
            .background(color = Color.White)
            .padding(0.dp)
            .fillMaxWidth()
    ) {

        turnoViewModel.getTurnaActivo()

        Text(
            text = namePlantilla,
            textAlign = TextAlign.Center,
            maxLines = 2,
            modifier = Modifier
                .padding(start = 40.dp, end = 40.dp, top = 8.dp),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.SemiBold
        )

        Text(
            text = "$nameLugar - $idPlantilla",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.spacer)))
        Divider( modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.inversePrimary)
        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.spacer)))

        Row (
            horizontalArrangement = Arrangement.Absolute.Left,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {
                val imageBytes = Base64.decode(base64, Base64.DEFAULT)
                val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

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
                        .border(
                            BorderStroke(0.dp, MaterialTheme.colorScheme.inversePrimary),
                            CircleShape
                        )
                        .clip(CircleShape)
                )
            }

            Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.spacer)))

            Column {

                Text(
                    text = "${floatFormat(idUser)}",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary
                )

                Text(
                    text = "$nombre $apellido",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary
                )

                Text(
                    text = "${currentDateTime()} - ${turnoActivo}",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }

        }

        /*
        //Geolocalización Funciona OK
        val activity = LocalContext.current as MainActivity
        LocationManager.Builder
            .create(activity)
            .request( onUpdateLocation =  { altitud: Double, latitud: Double , longitud: Double ->
                LocationManager.removeCallback(activity)
                locationViewModel.altitud.value = altitud
                locationViewModel.latitud.value = latitud
                locationViewModel.longitud.value = longitud
            })
        */

        //Valores de Geolocalización
        FilledCardExample(
            locationViewModel.altitud.value.toFloat(),
            locationViewModel.latitud.value.toFloat(),
            locationViewModel.longitud.value.toFloat()
        )

        Divider( modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.inversePrimary)
        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.spacer)))


        var lista =  plantillaDetViewModel.lugares.collectAsState().value.listLugares

        lista = lista.filter { it.plt_id == idPlantilla.toInt() }

        ListSitios(lista)

    }
}

@Composable
fun FilledCardExample(Alt: Float, Lat: Float, Lng: Float) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary,
        ),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()

    ) {
        Row (
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 4.dp, start = 16.dp, end = 16.dp)
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Altitud",
                    modifier = Modifier
                        .padding(2.dp),
                    //textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = String.format("%.2f", Alt),
                    modifier = Modifier
                        .padding(start = 4.dp, bottom = 4.dp),
                    //textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.inversePrimary
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            )  {
                Text(
                    text = "Latitud",
                    modifier = Modifier
                        .padding(2.dp),
                    //textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Text(
                    text = String.format("%.6f", Lat),
                    modifier = Modifier
                        .padding(start = 4.dp),
                    //textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.inversePrimary
                )
            }

            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = "Longitud",
                    modifier = Modifier
                        .padding(2.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimary
                )


                Text(
                    text = String.format("%.6f", Lng),
                    modifier = Modifier
                        .padding(start = 4.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.inversePrimary
                )
            }
        }
    }
}


@Composable
fun ListSitios(
    lugares: List<LugaresMuestraModel>
) {

    LazyRow (
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
    ){

        items(3) { index ->
            Text(
                modifier = Modifier.padding(all = 6.dp),
                text = "$index ..0.. ",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.inversePrimary
            )
        }


        items(lugares.size) { index ->
            Text(
                modifier = Modifier.padding(all = 6.dp),
                text = lugares[index].lug_nombre,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.inversePrimary
            )
        }
    }

}

