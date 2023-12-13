package com.dflch.water.screens


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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

                Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.spacer_padding)))

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
                    color = Color.Yellow,
                )
            }
        }

        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.header_padding)))

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {

            OutlinedButton(
                onClick = { navController.navigate(route = AppScreens.TasksScreen.route) },
                modifier = Modifier
                    .height(200.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                shape = RoundedCornerShape(10.dp)
            ) {
                Column (
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
                        "Plantas Tratamiento",
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Black,
                        maxLines = 1
                    )

                    Text(
                        "Toma de muestras fisico-químicas de agua, nivel rio, Nivel tanques ",
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
                    .height(200.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                shape = RoundedCornerShape(10.dp)
            ) {
                Column (
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
                        "Plantas Tratamiento",
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Black,
                        maxLines = 1
                    )

                    Text(
                        "Toma de muestras fisico-químicas de agua, nivel rio, Nivel tanques ",
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
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Stack", color = Color.Gray)
            Text(text = "Java", color = Color.Gray)
            Text(text = "Kotlin", color = Color.Gray)
        }



        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.spacer_padding)))

        Button(onClick = { navController.navigate(route = AppScreens.TasksScreen.route) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, top = 10.dp, bottom = 1.dp)
                .height(55.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            shape = RoundedCornerShape(15.dp)
        ) {
            Column (
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
                
            ) {
                Image(
                    painter = painterResource(id = androidx.core.R.drawable.ic_call_decline),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 5.dp)
                )
            }
            
            Column (
                modifier = Modifier
                    .padding(5.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    "Redes Acueducto",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold

                )
            }

        }
    }






}

