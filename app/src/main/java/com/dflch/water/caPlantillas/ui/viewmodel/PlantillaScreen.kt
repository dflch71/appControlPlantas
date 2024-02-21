package com.dflch.water.caPlantillas.ui.viewmodel


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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.dflch.water.R
import com.dflch.water.caPlantillas.ui.model.PlantillaModel
import com.dflch.water.caPlantillas.ui.model.SitioModel
import com.dflch.water.caPlantillasDet.ui.model.PlantillaDetModel
import com.dflch.water.caPlantillasDet.ui.viewmodel.PlantillaDetViewModel
import com.dflch.water.caPlantillasDet.ui.viewmodel.idx
import com.dflch.water.caUsers.ui.viewmodel.Item
import com.dflch.water.caUsers.ui.viewmodel.UserViewModel
import com.dflch.water.navigation.AppScreens
import com.dflch.water.ui.theme.WaterTheme
import com.dflch.water.utils.Constants.currentDateTime
import com.dflch.water.utils.Constants.floatFormat
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PlantillaScreen(
    userViewModel: UserViewModel,
    plantillaViewModel: PlantillaViewModel,
    navController: NavController )
{
    BodyContentMain(
        userViewModel,
        plantillaViewModel,
        navController,
        modifier = Modifier)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BodyContentMain(
    userViewModel: UserViewModel,
    plantillaViewModel: PlantillaViewModel,
    navController: NavController,
    modifier: Modifier) {

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
                //colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary, blendMode = BlendMode.Color)
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

        Text(
            text = "EMPRESAS MUNICIPALES DE CARTAGO",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.spacer)))
        MenuRow(plantillaViewModel, navController)
        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.spacer)))

        //Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.spacer)))
        //RowsMenu(plantillaViewModel, navController)
        //Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.spacer)))
    }
}


@Composable
fun RowsMenu(plantillaViewModel: PlantillaViewModel, navController: NavController) {

    val mTitle = remember { mutableStateOf("ESTACIÓN DE BOMBEO") }
    val mPlantilla = remember { mutableStateOf(3) }

    Column (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement  =  Arrangement.SpaceEvenly
        ){
            ElevatedCardOpc01("BOMBEO", R.drawable.bombeo) {
                mTitle.value = "ESTACIÓN DE BOMBEO"
                mPlantilla.value = 3
            }

            ElevatedCardOpc01("PLANTA 01", R.drawable.planta01 ) {
                mTitle.value = "PLANTA TRATAMIENTO 01"
                mPlantilla.value = 1
            }

            ElevatedCardOpc01("PLANTA 02", R.drawable.planta02 ) {
                mTitle.value = "PLANTA TRATAMIENTO 02"
                mPlantilla.value = 2
            }

        }

        Spacer(modifier = Modifier.padding(5.dp))

        Text(
            text = mTitle.value,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Divider( modifier = Modifier.padding(vertical = 4.dp) )
        Spacer(modifier = Modifier.padding(5.dp))

        ContentScaffold(plantillaViewModel, navController, mPlantilla.value)

        Divider( modifier = Modifier.padding(vertical = 4.dp) )
        Spacer(modifier = Modifier.padding(5.dp))
    }
}

@Composable
fun CardPlantilla(
    plantillaModel: PlantillaModel,
    index: Int,
    selectedIndex: Int,
    plantillaViewModel: PlantillaViewModel,
    navController: NavController,
    onClick: (Int) -> Unit
){

    val backgroundColor = if (index == selectedIndex) MaterialTheme.colorScheme.inversePrimary else MaterialTheme.colorScheme.background
    val ctx = LocalContext.current

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                onClick(index)
                plantillaViewModel.onItemSelectec(
                    navController,
                    index,
                    plantillaModel.plantilla,
                    plantillaModel.sitio,
                    plantillaModel.plt_id.toString(),
                    ctx
                )
            }
            .padding(horizontal = 16.dp, vertical = 4.dp),

        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
    ) {

        Column (
            modifier = Modifier.padding(16.dp)
        ){

            Text(
                text = plantillaModel.plantilla,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Start,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(bottom = 4.dp),
            )

            Divider( modifier = Modifier.padding(vertical = 4.dp) )

            Text(
                text = plantillaModel.sitio,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun ListVariables(
    smt: Int,
    plantillaViewModel: PlantillaViewModel,
    navController: NavController
) {
    var selectedIndex by remember { mutableStateOf(-1) }

    var listaPlantillas =  plantillaViewModel.state.collectAsState().value.listPlantillas
    listaPlantillas = if (smt < 3) listaPlantillas.filter { it.smt_id == smt }
                      else listaPlantillas.filter { it.smt_id >= smt }

    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(listaPlantillas.size) { item ->
            CardPlantilla(
                listaPlantillas[item],
                item,
                selectedIndex,
                plantillaViewModel,
                navController
            ) { i ->
                selectedIndex = i
                idx2 = i
            }
        }
        selectedIndex = idx2
    }
}

@Composable
private fun ContentScaffold(
    plantillaViewModel: PlantillaViewModel,
    navController: NavController,
    fTemplate: Int
) {
    val state by plantillaViewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (state.listPlantillas.isNotEmpty()) {

            var selectedIndex by remember { mutableStateOf(-1) }

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(0.dp)) {

                //val itemCount = state.listPlantillas.size

                items(state.listPlantillas.size) {

                    if (fTemplate < 3) {
                        if (state.listPlantillas[it].smt_id == fTemplate) {
                            CardPlantilla(
                                state.listPlantillas[it],
                                it,
                                selectedIndex,
                                plantillaViewModel,
                                navController
                            ) { i ->
                                selectedIndex = i
                            }
                        }
                    } else {
                        if (state.listPlantillas[it].smt_id >= fTemplate) {
                            CardPlantilla(
                                state.listPlantillas[it],
                                it,
                                selectedIndex,
                                plantillaViewModel,
                                navController
                            ) { i ->
                                selectedIndex = i
                            }
                        }

                    }
                }
            }

        } else {
            Text(text = "Lista ITEMS vacía")
        }
    }
}

@Preview
@Composable
fun PlantillaScreenPreview() {
    WaterTheme {
        ElevatedCardOpc01("Bombeo", R.drawable.planta01, onClick = {})
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ElevatedCardOpc01(
    titulo: String,
    image: Int,
    onClick: () -> Unit
    ){

    OutlinedCard(
        onClick = onClick,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor =  MaterialTheme.colorScheme.onTertiary,
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        modifier = Modifier
            .size(width = 125.dp, height = 130.dp)
            .padding(8.dp)
            .clickable {}
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // Miniatura
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                //colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.button_with))
                    .border(
                        BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                        CircleShape
                    )
                    .clip(CircleShape)
                    .align(alignment = Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                titulo,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                overflow = TextOverflow.Visible,
                softWrap = false,
                maxLines = 1
            )

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SitioMuestra(model: SitioModel, onClick: () -> Unit) {
    OutlinedCard(
        onClick = onClick,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor =  MaterialTheme.colorScheme.onTertiary,
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        modifier = Modifier
            .size(width = 125.dp, height = 130.dp)
            .padding(8.dp)
            .clickable {}
    ){
        Column(
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // Miniatura
            Image(
                painter = painterResource(id = model.image),
                contentDescription = null,
                //colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.button_with))
                    .border(
                        BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                        CircleShape
                    )
                    .clip(CircleShape)
                    .align(alignment = Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                model.nombreCorto,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                overflow = TextOverflow.Visible,
                softWrap = false,
                maxLines = 1
            )
        }
    }
}

var idx1: Int = 0     //Controlar posición LazyRow
var idx2: Int = -1    //Controlar posición GridRow
@Composable
private fun MenuRow(plantillaViewModel: PlantillaViewModel, navController: NavController) {

    val sitioList = mutableListOf<SitioModel>()

    sitioList.add(SitioModel("BOMBEO", "ESTACIÓN DE BOMBEO", R.drawable.bombeo,3))
    sitioList.add(SitioModel("PLANTA 1", "PLANTA DE TRATAMIENTO 01", R.drawable.planta01,1))
    sitioList.add(SitioModel("PLANTA 2", "PLANTA DE TRATAMIENTO 02", R.drawable.planta02, 2))

    val mTitle = remember { mutableStateOf(sitioList[idx1].nombreLargo) }
    val smtID = remember { mutableIntStateOf(sitioList[idx1].smtID) }

    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        state = scrollState
    )
    {
        coroutineScope.launch {
            scrollState.animateScrollToItem((idx1).coerceIn(0..(sitioList.size-1)))
        }

        items(sitioList.size) { sitio,  ->

            SitioMuestra(model = sitioList[sitio], onClick = {
                mTitle.value = sitioList[sitio].nombreLargo
                idx1 = sitio
                idx2 = -1
                smtID.value = sitioList[sitio].smtID
            })

        }
    }

    Spacer(modifier = Modifier.padding(5.dp))

    Text(
        text = mTitle.value,
        textAlign = TextAlign.Start,
        style = MaterialTheme.typography.titleSmall,
        color = MaterialTheme.colorScheme.secondary,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 4.dp),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )

    Divider( modifier = Modifier.padding(vertical = 4.dp) )
    Spacer(modifier = Modifier.padding(5.dp))

    ListVariables(smtID.value.toInt(), plantillaViewModel, navController)

}