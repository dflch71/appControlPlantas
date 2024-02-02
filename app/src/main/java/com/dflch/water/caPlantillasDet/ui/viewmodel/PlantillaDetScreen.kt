package com.dflch.water.caPlantillasDet.ui.viewmodel

import android.graphics.BitmapFactory
import android.os.Build
import android.util.Base64
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.dflch.water.R
import com.dflch.water.caPlantillas.ui.viewmodel.PlantillaViewModel
import com.dflch.water.caUsers.ui.viewmodel.UserViewModel
import com.dflch.water.utils.Constants

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PlantillaDetScreen (
    userViewModel: UserViewModel,
    plantillaViewModel: PlantillaViewModel,
    plantillaDetViewModel: PlantillaDetViewModel,
    navController: NavController
) {

    BodyContentMain(
        userViewModel = userViewModel,
        plantillaViewModel = plantillaViewModel,
        plantillaDetViewModel = plantillaDetViewModel,
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
    navController: NavController,
    modifier: Modifier
) {

    //userViewModel
    val idUser: String by userViewModel.idUser.observeAsState(initial = "")
    val nombre:String by userViewModel.nombre.observeAsState(initial = "Nombres")
    val apellido:String by userViewModel.apellido.observeAsState(initial = "Apellidos")
    val base64: String by userViewModel.base64.observeAsState(initial = "")

    //plantillaViewModel
    val namePlantilla: String by plantillaViewModel.namePlantilla.observeAsState(initial = "")
    val nameLugar: String by plantillaViewModel.nameLugar.observeAsState(initial = "")


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(MaterialTheme.colorScheme.primary)
            .clip(MaterialTheme.shapes.large)
            .background(color = Color.White)
            .padding(0.dp)
            .fillMaxWidth()
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
/*
            Image(
                painter = painterResource(id = R.drawable.top_background),
                contentDescription = null,
                //colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary, blendMode = BlendMode.Color)
            )
*/


            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){

                Text(
                    text = namePlantilla,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    modifier = Modifier
                        .padding(start = 40.dp, end = 40.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.inversePrimary
                )

                Text(
                    text = nameLugar,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.inversePrimary
                )


                Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.spacer)))
                Divider( modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.inversePrimary)
                Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.spacer)))

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
                        .size(dimensionResource(id = R.dimen.header_image_size))
                        .border(
                            BorderStroke(4.dp, MaterialTheme.colorScheme.inversePrimary),
                            CircleShape
                        )
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.spacer)))

                Text(
                    text = "${Constants.floatFormat(idUser)}",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.inversePrimary,
                )

                Text(
                    text = "$nombre $apellido",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.inversePrimary
                )

                Text(
                    text = "${Constants.currentDateTime()}",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Yellow,
                )
            }
        }

        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.spacer)))

        Text(
            text = "EMPRESAS MUNICIPALES DE CARTAGO",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )

        //Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.spacer)))
        //RowsMenu(plantillaDetViewModel, navController)
        //Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.spacer)))

    }
}

