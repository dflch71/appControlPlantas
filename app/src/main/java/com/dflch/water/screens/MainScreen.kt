package com.dflch.water.screens


import android.graphics.BitmapFactory
import android.os.Build
import android.util.Base64
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.dflch.water.R
import com.dflch.water.caUsers.ui.viewmodel.UserViewModel
import com.dflch.water.navigation.AppScreens
import com.dflch.water.utils.Constants.currentDateTime
import com.dflch.water.utils.Constants.floatFormat

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(userViewModel: UserViewModel, navController: NavController ) {
    BodyContentMain(userViewModel, navController, modifier = Modifier)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BodyContentMain(userViewModel: UserViewModel, navController: NavController, modifier: Modifier) {

    //userViewModel
    val idUser: String by userViewModel.idUser.observeAsState(initial = "")
    val nombre:String by userViewModel.nombre.observeAsState(initial = "Nombres")
    val apellido:String by userViewModel.apellido.observeAsState(initial = "Apellidos")
    val base64: String by userViewModel.base64.observeAsState(initial = "")

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            //.background(MaterialTheme.colorScheme.secondary)
            .background(color = Color.White)
            .padding(0.dp)
            .fillMaxWidth()
    ) {

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){

            Image(
                painter = painterResource(id = R.drawable.top_background),
                contentDescription = null,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                //colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary, blendMode = BlendMode.Color)
            )

            val imageBytes = Base64.decode(base64, Base64.DEFAULT)
            val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
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
                    text = "${floatFormat(idUser)}",
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
                    text = "${currentDateTime()}",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.inversePrimary
                )
            }
        }

        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.spacer)))
        ElevatedCardOpc01(navController)
        ElevatedCardOpc02(navController)
        ElevatedCardOpc03(navController)
        ElevatedCardOpc04(navController)
    }
}

@Composable
fun ElevatedCardOpc01(navController: NavController) {
    OutlinedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, bottom = 4.dp, top = 4.dp)
            .height(120.dp)
            .clickable { navController.navigate(route = AppScreens.PlantillaScreen.route) }
    ) {

        Row(
            Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(start = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Miniatura
            Image(
                painter = painterResource(id = R.drawable.treatment),
                contentDescription = null,
                //colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.button_with))
                    .border(
                        BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                        CircleShape
                    )
                    .padding(8.dp)
                    .clip(CircleShape)
                    .align(alignment = Alignment.CenterVertically)
            )

            // Miniatura
            /*Box(
                modifier = Modifier
                    .background(color = Color.LightGray, shape = CircleShape)
                    .size(80.dp),
                contentAlignment = Alignment.Center
            ) {

                Image(
                    painter = painterResource(id = R.drawable.treatment),
                    contentDescription = null,
                    //colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.image_logo))

                )
            }*/

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp)
            ) {

                // Encabezado
                Text(
                    "PLANTAS DE TRATAMIENTO",
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black,
                    overflow = TextOverflow.Visible,
                    softWrap = false,
                    maxLines = 1
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    "Toma de muestras físico-químicas de agua (cruda - tratada), nivel rio, nivel tanques ",
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    maxLines = 3
                )
            }
        }
    }
}

@Composable
fun ElevatedCardOpc02(navController: NavController) {
    OutlinedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, bottom = 4.dp, top = 4.dp)
            .height(120.dp)
            .clickable { navController.navigate(route = AppScreens.TasksScreen.route) }
    ) {

        Row(
            Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(start = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Miniatura
            Image(
                painter = painterResource(id = R.drawable.gauge),
                contentDescription = null,
                //colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.button_with))
                    .border(
                        BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                        CircleShape
                    )
                    .padding(8.dp)
                    .clip(CircleShape)
                    .align(alignment = Alignment.CenterVertically)
            )

            // Miniatura
            /*Box(
                modifier = Modifier
                    .background(color = Color.LightGray, shape = CircleShape)
                    .size(80.dp),
                contentAlignment = Alignment.Center
            ) {

                Image(
                    painter = painterResource(id = R.drawable.treatment),
                    contentDescription = null,
                    //colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.image_logo))

                )
            }*/

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp)
            ) {

                // Encabezado
                Text(
                    "ESTACIONES REGULADORAS",
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black,
                    overflow = TextOverflow.Visible,
                    softWrap = false,
                    maxLines = 1
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    "Sectorización, apertura y cierre de valvulas, suspensiones sectorizadas del servicio de agua",
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    maxLines = 3
                )

            }
        }
    }
}

@Composable
fun ElevatedCardOpc03(navController: NavController) {
    OutlinedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, bottom = 4.dp, top = 4.dp)
            .height(120.dp)
            .clickable { navController.navigate(route = AppScreens.TasksScreen.route) }
    ) {

        Row(
            Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(start = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Miniatura
            Image(
                painter = painterResource(id = R.drawable.plumbing),
                contentDescription = null,
                //colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.button_with))
                    .border(
                        BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                        CircleShape
                    )
                    .padding(8.dp)
                    .clip(CircleShape)
                    .align(alignment = Alignment.CenterVertically)
            )

            // Miniatura
            /*Box(
                modifier = Modifier
                    .background(color = Color.LightGray, shape = CircleShape)
                    .size(80.dp),
                contentAlignment = Alignment.Center
            ) {

                Image(
                    painter = painterResource(id = R.drawable.treatment),
                    contentDescription = null,
                    //colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.image_logo))

                )
            }*/

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp)
            ) {

                // Encabezado
                Text(
                    "REDES ACUEDUCTO",
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black,
                    overflow = TextOverflow.Visible,
                    softWrap = false,
                    maxLines = 1
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    "Relación de daños en las redes de acueducto, y labores de reparación de operarios en terreno",
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    maxLines = 3
                )
            }
        }
    }
}

@Composable
fun ElevatedCardOpc04(navController: NavController) {
    OutlinedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, bottom = 4.dp, top = 4.dp)
            .height(120.dp)
            .clickable { navController.navigate(route = AppScreens.TasksScreen.route) }
    ) {

        Row(
            Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(start = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Miniatura
            Image(
                painter = painterResource(id = R.drawable.water),
                contentDescription = null,
                //colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.button_with))
                    .border(
                        BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                        CircleShape
                    )
                    .padding(8.dp)
                    .clip(CircleShape)
                    .align(alignment = Alignment.CenterVertically)
            )

            // Miniatura
            /*Box(
                modifier = Modifier
                    .background(color = Color.LightGray, shape = CircleShape)
                    .size(80.dp),
                contentAlignment = Alignment.Center
            ) {

                Image(
                    painter = painterResource(id = R.drawable.treatment),
                    contentDescription = null,
                    //colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.image_logo))

                )
            }*/

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp)
            ) {

                // Encabezado
                Text(
                    "OPERACIÓN VACTOR",
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black,
                    overflow = TextOverflow.Visible,
                    softWrap = false,
                    maxLines = 1
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    "Recorridos de operación y limpiezas de redes de alcantarillado realizadas por el vactor en la ciudad",
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    maxLines = 3
                )
            }
        }
    }
}

@Composable
private fun MenuRow(navController: NavController) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        OutlinedButton(
            modifier = Modifier
                .weight(1f)
                .height(200.dp),
            onClick = { navController.navigate(route = AppScreens.TasksScreen.route) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                Image(
                    painter = painterResource(id = R.drawable.ic_factory_24),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.button_with))
                        .border(
                            BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                            CircleShape
                        )
                        .padding(16.dp)
                        .clip(CircleShape)
                        .align(alignment = Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    "PLANTAS TRATAMIENTO",
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Black,
                    overflow = TextOverflow.Visible,
                    softWrap = false,
                    maxLines = 1
                )

                Text(
                    "Toma de muestras físico-químicas de agua, nivel rio, nivel tanques ",
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.LightGray,
                    maxLines = 3
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        OutlinedButton(
            onClick = { navController.navigate(route = AppScreens.TasksScreen.route) },
            modifier = Modifier
                .weight(1f)
                .height(200.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                Image(
                    painter = painterResource(id = R.drawable.ic_factory_24),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.button_with))
                        .border(
                            BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                            CircleShape
                        )
                        .padding(16.dp)
                        .clip(CircleShape)
                        .align(alignment = Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    "Estaciones Reguladoras",
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Black,
                    maxLines = 1
                )

                Text(
                    "Control de apertura y cierre de valvulas, suspensiones del servicio de agua",
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.LightGray,
                    maxLines = 3
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        OutlinedButton(
            modifier = Modifier
                .weight(1f)
                .height(200.dp),
            onClick = { navController.navigate(route = AppScreens.TasksScreen.route) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                Image(
                    painter = painterResource(id = R.drawable.ic_factory_24),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.button_with))
                        .border(
                            BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                            CircleShape
                        )
                        .padding(16.dp)
                        .clip(CircleShape)
                        .align(alignment = Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    "Redes Acueducto",
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Black,
                    maxLines = 1
                )

                Text(
                    "Relación de daños en la red, y labores de operarios en terreno",
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.LightGray,
                    maxLines = 3
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        OutlinedButton(
            onClick = { navController.navigate(route = AppScreens.TasksScreen.route) },
            modifier = Modifier
                .weight(1f)
                .height(200.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                Image(
                    painter = painterResource(id = R.drawable.ic_factory_24),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.button_with))
                        .border(
                            BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                            CircleShape
                        )
                        .padding(16.dp)
                        .clip(CircleShape)
                        .align(alignment = Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    "Estadísticas",
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Black,
                    maxLines = 1
                )

                Text(
                    "Informe on-line de actividades y recursos de la red",
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.LightGray,
                    maxLines = 3
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}