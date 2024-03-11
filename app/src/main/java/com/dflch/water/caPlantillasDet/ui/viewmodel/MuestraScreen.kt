package com.dflch.water.caPlantillasDet.ui.viewmodel

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dflch.water.R
import com.dflch.water.caPlantillas.ui.viewmodel.PlantillaViewModel
import com.dflch.water.utils.Constants.floatFormatDecimal
import kotlinx.coroutines.delay

@Composable
fun MuestraScreen (
    plantillaViewModel: PlantillaViewModel,
    plantillaDetViewModel: PlantillaDetViewModel,
    navController: NavController
)
{
    val vrMin = plantillaDetViewModel.car_vrMin.value
    val vrMax = plantillaDetViewModel.car_vrMax.value
    val lectura = plantillaDetViewModel.car_lectura.value
    val pld_ID = plantillaDetViewModel.pldID.value

    ContentScaffold(
        plantillaViewModel,
        plantillaDetViewModel,
        navController,
        vrMin!!,
        vrMax!!,
        lectura!!)
}

@Composable
private fun BodyContent(
    plantillaViewModel: PlantillaViewModel,
    plantillaDetViewModel: PlantillaDetViewModel,
    navController: NavController
) {
    //plantillaViewModel
    val nameLugar: String by plantillaViewModel.nameLugar.observeAsState(initial = "")

    //plantillaDetViewModel
    val pldID: Int by plantillaDetViewModel.pldID.observeAsState(initial = 0)
    val lug_nombre: String by plantillaDetViewModel.lug_nombre.observeAsState(initial = "")
    val car_nombre: String by plantillaDetViewModel.car_nombre.observeAsState(initial = "")
    val car_expresado: String by plantillaDetViewModel.car_expresado.observeAsState(initial = "")
    val car_unidad: String by plantillaDetViewModel.car_unidad.observeAsState(initial = "")
    val car_vrMin: Float by plantillaDetViewModel.car_vrMin.observeAsState(initial = 0.0f)
    val car_vrMax: Float by plantillaDetViewModel.car_vrMax.observeAsState(initial = 0.0f)
    val car_lectura: Float by plantillaDetViewModel.car_lectura.observeAsState(initial = 0.0f)
    val car_exportado: Boolean by plantillaDetViewModel.car_exportado.observeAsState(initial = false)

    var lectura by rememberSaveable { mutableFloatStateOf(0f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Text(
            text = car_nombre,
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1
        )

        Text(
            text = car_expresado,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary,
            maxLines = 1
        )

        Text(
            text = lug_nombre,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary,
            maxLines = 1
        )

        Text(
            text = nameLugar,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Light,
            maxLines = 2
        )

        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.spacer)))
        Divider(modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.inversePrimary)
        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.spacer)))

        //lectura = ValorMuestra(car_vrMin, car_vrMax)
        ValorMuestra(car_vrMin, car_vrMax, plantillaDetViewModel)

        //Mostar los rangos del valor de la muestra
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 8.dp),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {
            Text(
                text = "Vr Mínimo($car_unidad)",
                modifier = Modifier.align(Alignment.CenterVertically),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary,
                maxLines = 1
            )
            Text(
                text = "Vr Máximo($car_unidad)",
                modifier = Modifier.align(Alignment.CenterVertically),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary,
                maxLines = 1
            )
        }

        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.spacer)))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {
            Text(
                text = floatFormatDecimal(car_vrMin.toString()),
                modifier = Modifier.align(Alignment.CenterVertically),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary,
                maxLines = 1,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                text = floatFormatDecimal(car_vrMax.toString()),
                modifier = Modifier.align(Alignment.CenterVertically),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary,
                maxLines = 1,
                fontWeight = FontWeight.SemiBold,
            )
        }

        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.spacer)))
        Divider(modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.inversePrimary)
        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.spacer)))

        //Botones de aceptar y cancelar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {

            val currentContext = LocalContext.current
            OutlinedButton(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 4.dp)

            ) {
                Text("CANCELAR")
            }

            var listaVar =  plantillaDetViewModel.state.collectAsState().value.listItems
            val items by plantillaDetViewModel.allItems.observeAsState()


            FilledTonalButton(
                onClick = {

                    if (!plantillaDetViewModel.validarLectura(lectura)) {

                        for (variable in listaVar) {
                            plantillaDetViewModel.updateLectura(variable, lectura.toDouble())
                        }

                        //plantillaDetViewModel.updateLectura(listaVar, lectura.toDouble())
                        //Actualizar BD Nube

                        navController.popBackStack()
                    } else {
                        Toast.makeText(
                            currentContext,
                            "El valor de la muestra debe estar entre $car_vrMin y $car_vrMax",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 4.dp)

            ) {
                Text("ACEPTAR")
            }
        }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ValorMuestra(
    vrMin: Float = 0.0f,
    vrMax: Float = 0.0f,
    plantillaDetViewModel: PlantillaDetViewModel
) {
    var text by remember { mutableStateOf("") }
    val isVisible by remember { derivedStateOf { text.isNotBlank() } }
    val showKeyboard = remember { mutableStateOf(true) }
    val focusRequester = remember { FocusRequester() }
    val keyboard = LocalSoftwareKeyboardController.current

    //Validation values min and max
    var isValueMinMax by remember { mutableStateOf(false) }

    Column {

        OutlinedTextField(

            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                .fillMaxWidth()
                .height(100.dp)
                .focusRequester(focusRequester),

            value = text,
            onValueChange = { newText ->
                                text = newText.trimStart{ it == '0' }
                                showKeyboard.value = true
                                isValueMinMax = false
                            },
            isError = isValueMinMax,
            singleLine = true,
            label = { Text("Valor Muestra") },
            textStyle = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            ),
            shape = MaterialTheme.shapes.extraSmall,
            visualTransformation = VisualTransformation.None,
            maxLines = 1,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.inversePrimary, // Custom focused border color
                unfocusedBorderColor = MaterialTheme.colorScheme.primary, // Custom unfocused border color
            ),

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next
            ),

            keyboardActions = KeyboardActions {

                isValueMinMax = validateRange(vrMin, vrMax, text.toFloat(), plantillaDetViewModel)
                if (!isValueMinMax) {
                    showKeyboard.value = false
                    keyboard?.hide()
                }

            },

            /*trailingIcon = {
                if (isVisible) {
                    IconButton(
                        onClick = {
                            text = ""
                            showKeyboard.value = true
                            keyboard?.show()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear"
                        )
                    }
                }
            },*/
        )

        if (isValueMinMax) {
            Text(
                text = "* VALOR FUERA DEL RANGO",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
            )
        }

        // LaunchedEffect prevents endless focus request
        LaunchedEffect(focusRequester) {
            if (showKeyboard.value == true) {
                focusRequester.requestFocus()
                delay(100) // Make sure you have delay here
                keyboard?.show()
            }
        }
    }

    //    if (!text.isEmpty() || !text.isBlank()) {
    //        if (text.toFloat() > 0.0f) {
    //            try {
    //                plantillaDetViewModel.updateLectura(
    //                    text.toFloat(),
    //                    false,
    //                    plantillaDetViewModel.pltID.value!!
    //                )
    //            } catch (e: Exception) {
    //                Log.e("Error", e.toString())
    //            }
    //        }
    //    }

    //return if (text.isEmpty() || text.isBlank()) { 0.0f }
    //else { text.toFloat() }
}

fun validateRange(vrMin: Float, vrMax: Float, lectura: Float, plantillaDetViewModel: PlantillaDetViewModel): Boolean {

    //    var validate: Boolean = true
    //
    //    if ((vrMin == 0.0f) && (vrMax == 0.0f)) {
    //        validate = false
    //    } else {
    //        if ((cad >= vrMin) && (cad <= vrMax)) {
    //            validate = false
    //        } else {
    //            validate = true
    //        }
    //    }
    //
    //    return validate

    var validate: Boolean
    validate = if ((vrMin == 0.0f) && (vrMax == 0.0f)) { false }
               else { !((lectura >= vrMin) && (lectura <= vrMax)) }

    if (!validate) {
        /*
        plantillaDetViewModel.updateLectura(
            lectura,
            false,
            plantillaDetViewModel.pldID.value!!
        )
        */
    }

    return validate

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentScaffold(
    plantillaViewModel: PlantillaViewModel,
    plantillaDetViewModel: PlantillaDetViewModel,
    navController: NavController,
    car_VrMin: Float,
    car_VrMax: Float,
    car_Lectura: Float
) {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val currentContext = LocalContext.current

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Toma de Muestra - Variables",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            //navController.popBackStack()
                            if (!plantillaDetViewModel.validarLectura(car_Lectura)) {
                                //Actualizar el valor de la muestra
                                //plantillaDetViewModel.updateLectura(car_Lectura, false, plt_ID)

                                //Actualizar BD Nube

                                navController.popBackStack()
                            } else {
                                Toast.makeText(
                                    currentContext,
                                    "El valor de la muestra debe estar entre $car_VrMin y $car_VrMax",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    ){
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },

//                actions = {
//                    IconButton(onClick = {}
//                    ) {
//                        Icon(
//                            imageVector = Icons.Filled.Menu,
//                            contentDescription = "Localized description"
//                        )
//                    }
//                },

                scrollBehavior = scrollBehavior,
            )
        },
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            BodyContent(plantillaViewModel, plantillaDetViewModel, navController)
        }
    }
}




