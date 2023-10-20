package com.dflch.water.screens.drawer

import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.dflch.water.R
import com.dflch.water.caFoto.ui.viewmodel.FotoViewModel
import com.dflch.water.caItems.ui.viewmodel.ItemViewModel
import com.dflch.water.caUsers.ui.viewmodel.UserViewModel


@Composable
fun AppDrawer(
    route: String,
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit = {},
    navigateToSettings: () -> Unit = {},
    closeDrawer: () -> Unit = {},

    userViewModel: UserViewModel,
    fotoViewModel: FotoViewModel
) {
    ModalDrawerSheet(modifier = Modifier) {
        DrawerHeader(modifier, userViewModel, fotoViewModel)
        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.spacer_padding)))
        NavigationDrawerItem(
            label = {
                Text(
                    text = stringResource(id = R.string.home),
                    style = MaterialTheme.typography.labelSmall
                )
            },
            selected = route == AllDestinations.HOME,
            onClick = {
                navigateToHome()
                closeDrawer()
            },
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = null) },
            shape = MaterialTheme.shapes.small
        )

        NavigationDrawerItem(
            label = { Text(text = stringResource(id = R.string.settings), style = MaterialTheme.typography.labelSmall) },
            selected = route == AllDestinations.SETTINGS,
            onClick = {
                navigateToSettings()
                closeDrawer()
            },
            icon = { Icon(imageVector = Icons.Default.Person, contentDescription = null) },
            shape = MaterialTheme.shapes.small
        )
    }
}


@Composable
fun DrawerHeader(modifier: Modifier, userViewModel: UserViewModel, fotoViewModel: FotoViewModel) {

    //userViewModel
    val idUser: String by userViewModel.idUser.observeAsState(initial = "")
    val nombre:String by userViewModel.nombre.observeAsState(initial = "Nombres")
    val apellido:String by userViewModel.apellido.observeAsState(initial = "Apellidos")

    //fotoViewModel
    val idUserFoto: String by fotoViewModel.idUserFoto.observeAsState(idUser)
    val base64: String by userViewModel.base64.observeAsState(initial = "")
    val viewModel: FotoViewModel = viewModel { fotoViewModel }
    val state by viewModel.stateFoto.collectAsState(
        initial = ItemViewModel.UiStateItem(
            loading = true,
            status = "Success"
        )
    )

    val img64 = base64
    val imageBytes = Base64.decode(img64, Base64.DEFAULT)
    val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .background(MaterialTheme.colorScheme.secondary)
            .padding(dimensionResource(id = R.dimen.header_padding))
            .fillMaxWidth()
    ) {

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            AsyncImage(
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(decodedImage)
                    .error(R.drawable.fb)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(dimensionResource(id = R.dimen.header_image_size))
                    .clip(CircleShape)

            )
        }

        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.spacer_padding)))

        Text(
            text = "$idUser",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary,
        )

        Text(
            text = "$nombre $apellido",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary,
        )

        /*Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = decodedImage,
                modifier = Modifier
                    .size(120.dp)
                    .border(
                        width = 1.dp, color = Color.LightGray, shape = CircleShape
                    )
                    .padding(3.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                error = painterResource(R.drawable.azul3),
                alignment = Alignment.Center
            )
        }*/

    }
}



