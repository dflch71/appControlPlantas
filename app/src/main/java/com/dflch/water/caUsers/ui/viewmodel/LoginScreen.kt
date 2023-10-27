package com.dflch.water.caUsers.ui.viewmodel

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dflch.water.R
import com.dflch.water.screens.template.AlertDialogError
import com.dflch.water.utils.network.ConnectivityObserver
import com.dflch.water.utils.network.NetworkConnectivityObserver


@Composable
fun LoginScreen(
    userViewModel: UserViewModel,
    navController: NavController)
{
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        val isLoading: Boolean by userViewModel.isLoading.observeAsState(initial = false)

        if (isLoading) {
            Box(
                Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
            ) {
                CircularProgressIndicator()
            }
        } else {
            Header(Modifier.align(Alignment.TopEnd))
            Body(Modifier.align(Alignment.Center), userViewModel, navController)
            Footer(userViewModel, Modifier.align(Alignment.BottomCenter))
        }
    }
}

@Composable
fun Footer(userViewModel: UserViewModel,  modifier: Modifier) {

    val context = LocalContext.current
    lateinit var connectivityObserver: ConnectivityObserver
    connectivityObserver = NetworkConnectivityObserver(context)
    val statusRed by connectivityObserver.observe().collectAsState(
        initial = ConnectivityObserver.Status.Desconectado
    )

    val status: String by userViewModel.status.observeAsState(initial = "Success ...")

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primary)
    ) {

        //Spacer(modifier = Modifier.size(24.dp))
        //SingUp()
        //Spacer(modifier = Modifier.size(12.dp))

        var redWS: Boolean = true
        if (statusRed != ConnectivityObserver.Status.Conectado) { redWS = false }

        var redWF: Boolean = true
        if (status != "Success") { redWF = false }

        if (!redWF || !redWS) {
            Divider(
                Modifier
                    .background(Color(0xFFF9F9F9))
                    .height(1.dp)
                    .fillMaxWidth()
            )
        }

        imageStatuseRed(redWF, redWS)
    }
}

@Composable
fun imageStatuseRed(Red: Boolean, WF: Boolean) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center)
    {
        if (!Red) {
            Text(text = "Web Service Off", fontSize = 12.sp, color = Color.White, modifier = Modifier.padding(4.dp))
            ImageWS()
        }
        if (!WF) {
            Text(text = "WiFi, Datos Off", fontSize = 12.sp, color = Color.White, modifier = Modifier.padding(4.dp))
            ImageRed()
        }
    }
}

@Composable
fun ImageRed() {
    Image(
        painter = painterResource(id = R.drawable.ic_wifi_off),
        contentDescription = "logo",
        contentScale = ContentScale.Fit,
        colorFilter = ColorFilter.tint(Color.White),
        modifier = Modifier
            .size(24.dp)
            .padding(4.dp)
    )
}

@Composable
fun ImageWS() {
    Image(
        painter = painterResource(id = R.drawable.ic_cloud_off),
        contentDescription = "logo",
        contentScale = ContentScale.Fit,
        colorFilter = ColorFilter.tint(Color.White),
        modifier = Modifier
            .size(24.dp)
            .padding(4.dp)
    )
}


@Composable
fun SingUp() {
    Row(Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = "Tienes cuenta?", fontSize = 12.sp, color = Color(0xFFB5B5B5))
        Text(
            text = "Registrarse.",
            Modifier.padding(horizontal = 8.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4EA8E9),
        )
    }
}

@Composable
fun Body(modifier: Modifier, userViewModel: UserViewModel, navController: NavController) {

    val idUser: String by userViewModel.idUser.observeAsState(initial = "")
    val password: String by userViewModel.password.observeAsState(initial = "")
    val isLoginEnabled: Boolean by userViewModel.isLoginEnable.observeAsState(initial = false)

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
    ) {
        ImageLogo(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.size(16.dp))
        IdUser(idUser) { userViewModel.onLoginChanged(idUser = it, password = password) }
        Spacer(modifier = Modifier.size(8.dp))
        Password(password) {userViewModel.onLoginChanged(idUser = idUser, password = it) }
        Spacer(modifier = Modifier.size(8.dp))
        //ForgotPassword(Modifier.align(Alignment.End))
        //Spacer(modifier = Modifier.size(16.dp))
        loginButton(isLoginEnabled, userViewModel, navController )
        //Spacer(modifier = Modifier.size(16.dp))
        //LoginDivider()
        //Spacer(modifier = Modifier.size(24.dp))
        //SocialLogin()
    }
}

@Composable
fun SocialLogin() {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.fb),
            contentDescription = "Social login fb",
            modifier = Modifier.size(16.dp)
        )
        Text("Continuar Facebook",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier =  Modifier.padding(horizontal = 8.dp),
            color = Color(0xFF4EA8E9)
        )
    }
}

@Composable
fun LoginDivider() {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .weight(1f)
        )
        Text(
            text = "O",
            modifier = Modifier.padding(horizontal = 18.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(colorText)
        )
        Divider(
            Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .weight(1f)
        )
    }
}

@Composable
fun loginButton(loginEnabled: Boolean, userViewModel: UserViewModel, navController: NavController) {

    val isErrorLogin: Boolean by userViewModel.isErrorLogin.observeAsState(initial = false)

    Button(
        onClick = { userViewModel.onLoginSelectec(navController) },
        enabled = loginEnabled,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF4EA8E9),
            disabledContainerColor = Color.LightGray,
            contentColor = Color.White,
            disabledContentColor = Color.White
        )
    ) {
        Text("INICIAR SESIÓN")
    }

    if (isErrorLogin){
        val openAlertDialog = remember { mutableStateOf(true) }
        when {
            openAlertDialog.value -> {
                AlertDialogError(
                    onDismissRequest = { openAlertDialog.value = false },
                    onConfirmation = { openAlertDialog.value = false },
                    dialogTitle = "LOGIN",
                    dialogText = "Verificar los valores digitados.\nNÚMERO_ID y/o CONTRASEÑA\nDigitado Incorrectamente",
                    icon = Icons.Default.ErrorOutline
                )
            }
        }
    }
}

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        text = "Olvido Credenciales?",
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF4EA8E9),
        modifier = modifier
    )
}

//Color empieza 0XFF
val colorBG = 0xFFE8F0F8
val colorText = 0xFF7E8284


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IdUser(idUser: String, onTextChanged: (String) -> Unit) {
   /* TextField(
        value = idUser,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Identificación") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
        colors = TextFieldDefaults.textFieldColors(
            //textColor = Color(colorText),
            containerColor = Color(colorBG),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            //placeholderColor = Color.LightGray
        )
    )*/

    OutlinedTextField(
        value = idUser,
        onValueChange = { onTextChanged(it) },
        label = { Text(text ="Número ID") },
        placeholder = { Text(text = "ID") },
        shape = RoundedCornerShape(8.dp),
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor =  Color(0xFFABB3BB),
            unfocusedBorderColor = Color(0xFFD0D0D0)
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "ID",
                tint = Color(0xFFD0D0D0)
            )
        },
        modifier = Modifier.fillMaxWidth(),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Password(password: String, onTextChanged: (String) -> Unit) {
    var passwordVisibility by remember { mutableStateOf(false) }

    val visibilityIcon = if (passwordVisibility)
        Icons.Filled.Visibility
    else Icons.Filled.VisibilityOff
    val description = if (passwordVisibility) "Ocultar Contraseña" else "Mostrar Contraseña"

    OutlinedTextField(
        value = password,
        onValueChange = { onTextChanged(it) },
        label = { Text(text = "Contraseña") },
        placeholder = { Text(text = "Contraseña") },
        shape = RoundedCornerShape(8.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor =  Color(0xFFABB3BB),
            unfocusedBorderColor = Color(0xFFD0D0D0)
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "Email Icon",
                tint = Color(0xFFD0D0D0)
            )
        },
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = if (passwordVisibility) VisualTransformation.None
        else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(imageVector = visibilityIcon, description)
            }
        }
    )

    /*TextField(
        value = password,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text( text = "Contraseña") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.textFieldColors(
            //textColor = Color(colorText),
            containerColor = Color(colorBG),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            //placeholderColor = Color.LightGray
        ),
       trailingIcon = {
           val image = if (passwordVisibility) { Icons.Filled.VisibilityOff }
            else { Icons.Filled.Visibility }
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(imageVector = image, contentDescription = "Show password")
            }
        },
        visualTransformation = if (passwordVisibility) { VisualTransformation.None }
        else { PasswordVisualTransformation() }
    )*/
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Email(email: String, onTextChanged: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Email") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.textFieldColors(
            //textColor = Color(0xFFB2B2B2),
            containerColor = Color(0xFFFAFAFA),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun ImageLogo(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.logoemc),
        contentDescription = "logo",
        contentScale = ContentScale.Fit,
        modifier = modifier
            .size(250.dp, 250.dp)
            .padding(bottom = 0.dp),
    )
}

@Composable
fun Header(modifier: Modifier) {
    val activity = LocalContext.current as Activity
    Icon(
        imageVector = Icons.Default.Close,
        contentDescription = "Close app",
        modifier = modifier.clickable {
            activity.finish()
        }
    )
}




