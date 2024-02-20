package com.dflch.water.caPlantillasDet.ui.viewmodel

import android.view.KeyEvent.ACTION_DOWN
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dflch.water.R
import kotlinx.coroutines.delay


@Composable
fun MuestraScreen (plantillaDetViewModel: PlantillaDetViewModel)
{
    //plantillaDetViewModel
    val lug_nombre: String by plantillaDetViewModel.lug_nombre.observeAsState(initial = "")
    val car_nombre: String by plantillaDetViewModel.car_nombre.observeAsState(initial = "")
    val car_expresado: String by plantillaDetViewModel.car_expresado.observeAsState(initial = "")
    val car_unidad: String by plantillaDetViewModel.car_unidad.observeAsState(initial = "")
    val car_vrMin: Float by plantillaDetViewModel.car_vrMin.observeAsState(initial = 0.0f)
    val car_vrMax: Float by plantillaDetViewModel.car_vrMax.observeAsState(initial = 0.0f)
    val car_lectura: Float by plantillaDetViewModel.car_lectura.observeAsState(initial = 0.0f)
    val car_exportado: Boolean by plantillaDetViewModel.car_exportado.observeAsState(initial = false)

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally

    ){

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

        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.spacer)))
        Divider( modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.inversePrimary)
        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.spacer)))

        ValorMuestra()
    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun ValorMuestra() {
    var text by remember { mutableStateOf("") }
    val isVisible by remember { derivedStateOf { text.isNotBlank() } }

    val showKeyboard = remember { mutableStateOf(true) }
    val focusRequester = remember { FocusRequester() }
    val keyboard = LocalSoftwareKeyboardController.current

    OutlinedTextField(

        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(100.dp)
            .focusRequester(focusRequester),

        value = text,
        onValueChange = { newText -> text = newText },
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
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

        trailingIcon = {
            if (isVisible) {
                IconButton(
                    onClick = { text = "" }
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear"
                    )
                }
            }
        }
    )

    // LaunchedEffect prevents endless focus request
    LaunchedEffect(focusRequester) {
        if (showKeyboard.value == true) {
            focusRequester.requestFocus()
            delay(100) // Make sure you have delay here
            keyboard?.show()
        }
    }
}