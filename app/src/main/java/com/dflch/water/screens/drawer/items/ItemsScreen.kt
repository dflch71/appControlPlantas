package com.dflch.water.screens.drawer.items


import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dflch.water.R
import com.dflch.water.caItems.ui.model.ItemModel
import com.dflch.water.caItems.ui.viewmodel.ItemViewModel
import com.dflch.water.navigation.AppScreens
import java.text.NumberFormat
import java.util.Locale



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemsScreen(
    itemViewModel: ItemViewModel,
    navController: NavController
)
{
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    val edgeToEdgeEnabled by remember { mutableStateOf(false) }

    itemViewModel.updItemsDB()

    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier.height(56.dp),
                actions = {

                            IconButton(onClick = { showBottomSheet = true }) {
                                Icon(
                                    Icons.Filled.KeyboardArrowUp,
                                    contentDescription = "Localized description"
                                )
                            }

                            Text(text = "Opciones")


                    /*      IconButton(onClick = { /* do something */ }) {
                                Icon(
                                    Icons.Filled.Edit,
                                    contentDescription = "Localized description",
                                )
                            }
                            IconButton(onClick = { /* do something */ }) {
                                Icon(
                                    Icons.Filled.Mic,
                                    contentDescription = "Localized description",
                                )
                            }
                            IconButton(onClick = { /* do something */ }) {
                                Icon(
                                    Icons.Filled.Image,
                                    contentDescription = "Localized description",
                                )
                            }
                    */
                },

                /*
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { showBottomSheet = true },
                        //containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                        //containerColor = MaterialTheme.colorScheme.primary,
                        containerColor = Color.LightGray,
                        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                    ) {
                        Icon(Icons.Filled.FilterList, "Localized description")
                    }
                }
                */
            )
        },
    ) { contentPadding ->
        // Screen content
        ContentScaffold(
            itemViewModel,
            modifier = Modifier.padding(contentPadding),
            navController
        )

        if (showBottomSheet) {

            val windowInsets = if (edgeToEdgeEnabled)
                WindowInsets(0) else BottomSheetDefaults.windowInsets

            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState,
                windowInsets = windowInsets,
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    // Sheet content
                    /*Button(onClick = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showBottomSheet = false
                            }
                        }
                    }) {}
                    */

                    BottomSheetContent(itemViewModel)
                }
            }
        }
    }
}

@Composable
fun BottomSheetListItem(icon: Int, title: String, onItemClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onItemClick(title) })
            .height(55.dp)
            .background(color = MaterialTheme.colorScheme.secondary)
            .padding(start = 15.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(painter = painterResource(id = icon), contentDescription = "Share", tint = Color.White)
        Spacer(modifier = Modifier.width(20.dp))
        Text(text = title, color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun BottomSheetListItemPreview() {
    BottomSheetListItem(icon = R.drawable.ic_launcher_foreground, title = "Share", onItemClick = { })
}

@Composable
fun BottomSheetContent(itemViewModel: ItemViewModel) {
    val context = LocalContext.current
    Column (modifier = Modifier.padding(bottom = 52.dp)) {

        BottomSheetListItem(
            icon = R.drawable.ic_filter_1,
            title = "Items Acueducto",
            onItemClick = { title ->
                Toast.makeText(
                    context,
                    title,
                    Toast.LENGTH_SHORT
                ).show()
                itemViewModel.getItemsActoDB()
            })

        BottomSheetListItem(
            icon = R.drawable.ic_filter_2,
            title = "Items Alcantarillado",
            onItemClick = { title ->
                Toast.makeText(
                    context,
                    title,
                    Toast.LENGTH_SHORT
                ).show()
                itemViewModel.getItemsAlcantDB()
            })

        BottomSheetListItem(
            icon = R.drawable.ic_filter_3,
            title = "Items Emcartago",
            onItemClick = { title ->
                Toast.makeText(
                    context,
                    title,
                    Toast.LENGTH_SHORT
                ).show()
                itemViewModel.getItemsDB()
            })

        Divider()

        BottomSheetListItem(
            icon = R.drawable.ic_swipe_up,
            title = "Orden Ascendente",
            onItemClick = { title ->
                Toast.makeText(
                    context,
                    title,
                    Toast.LENGTH_SHORT
                ).show()
            })

        BottomSheetListItem(
            icon = R.drawable.ic_swipe_down,
            title = "Orden Descendente",
            onItemClick = { title ->
                Toast.makeText(
                    context,
                    title,
                    Toast.LENGTH_SHORT
                ).show()
            })
    }
}

@Composable
private fun ContentScaffold(
    itemViewModel: ItemViewModel,
    modifier: Modifier,
    navController: NavController
){
    val state by itemViewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 56.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        SearchBarItem(itemViewModel)

        if (state.listItems.isNotEmpty()) {

            var selectedIndex by remember { mutableStateOf(-1) }


           LazyColumn(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                val itemCount = state.listItems.size

                items(itemCount) {
                    val item = state.listItems[it]

                    CardItem(item, it, selectedIndex, itemViewModel, navController) { i ->
                        selectedIndex = i
                    }
                }
            }


            /*val ctx = LocalContext.current

            LazyColumn(verticalArrangement = Arrangement.spacedBy(4.dp)) {

                itemsIndexed(
                    items = state.listItems
                ) { index, item ->

                    CardItem(item, index, selectedIndex, itemViewModel, navController) { i ->
                        selectedIndex = i
                    }
                }

            }*/



        } else {
            Text( text = "Lista ITEMS vacía" )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarItem(itemViewModel: ItemViewModel){

    val state by itemViewModel.state.collectAsState()

    val ctx = LocalContext.current

    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    val onSearch: (String) -> Unit = {
        Toast.makeText(ctx, query, Toast.LENGTH_SHORT).show()
        active = false
    }

    SearchBar(
        query = query,
        onQueryChange = { query = it },
        onSearch = onSearch,
        active = active,
        onActiveChange = { active = it },
        modifier = Modifier
            .wrapContentHeight()
            .padding(8.dp),
        placeholder = { Text(text = "Buscar") },
        leadingIcon = {
                IconButton(
                    onClick = { /*onSearch(query)*/ },
                    enabled = query.isNotEmpty()
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = ""
                    )
                }
        },

        trailingIcon = {
            Row {
                IconButton(onClick = { /* open mic dialog */ }) {
                    Icon(
                        imageVector = Icons.Filled.Mic,
                        contentDescription = "Mic")
                }
                if (active) {
                    IconButton(
                        onClick = { if (query.isNotEmpty()) query = "" else active = false }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Close"
                        )
                    }
                }
            }
        }

    ) {

        if (query.isNotEmpty()) {

            /*
            Proceso funciona, pendiente la asignación de los datos en LAZYCOLUMN
            data class Product(
                val itemDesc: String,
                val itemCosa: String,
                val itemUn: String,
                val itemValor: Double
            )

            val listProduct = state.listItems.map {
                listOf(
                    Product(it.itemDesc, it.itemCosa, it.itemUn, it.itemValor)
                )
            }

            //Buscar por la Descripción o por el Código
            val filteredProduct = listProduct.filter {
                it[0].itemDesc.contains(query.uppercase(), true) ||
                it[0].itemCosa.contains(query.uppercase(), true)
            }*/

            val listProduct = state.listItems.map {
                listOf(
                    it.itemDesc.uppercase(),
                    it.itemCosa,
                    it.itemUn,
                    it.itemValor
                )
            }
            //Buscar por la Descripción o por el Código
            val filteredProduct = listProduct.filter {
                        it[0].toString().contains(query.uppercase(), true) ||
                        it[1].toString().contains(query.uppercase(), true)
            }

            var selectedIndex by remember { mutableStateOf(-1) }

            if (filteredProduct.isEmpty()) {
                ListItemsEmpty(query)
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {

                    itemsIndexed(items = filteredProduct) { index, item ->

                        val backgroundColor = if (index == selectedIndex) MaterialTheme.colorScheme.inversePrimary else MaterialTheme.colorScheme.background

                        OutlinedCard(
                            colors = CardDefaults.cardColors(
                                containerColor = backgroundColor,
                            ),
                            border = BorderStroke(1.dp, Color.LightGray),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 4.dp)
                                .clickable { selectedIndex = index }

                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {

                                Text(
                                    text = "${filteredProduct[index][0]}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    textAlign = TextAlign.Start,
                                    maxLines = 3,
                                    modifier = Modifier.padding(bottom = 4.dp),
                                )

                                Divider()

                                Row(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(top = 4.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {

                                    val precio =
                                        NumberFormat.getCurrencyInstance(Locale("US", "us"))
                                            .format(filteredProduct[index][3])

                                    Text(
                                        text = "${filteredProduct[index][1]}",
                                        style = MaterialTheme.typography.labelMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )

                                    Text(
                                        text = "${filteredProduct[index][2]}: $precio",
                                        style = MaterialTheme.typography.labelLarge
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ListItemsEmpty(query: String) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "No existen ITEMS",
            style = MaterialTheme.typography.titleSmall
        )
        Text(
            text = "Intente ajustando la busqueda (${query.uppercase()})",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun CardItem(itemModel: ItemModel, index: Int, selectedIndex: Int, itemViewModel: ItemViewModel, navController: NavController, onClick: (Int) -> Unit ) {

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
                itemViewModel.onItemSelectec(
                    navController,
                    index,
                    itemModel.itemDesc,
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
                text = "${itemModel.itemDesc}",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Start,
                maxLines = 3,
                modifier = Modifier.padding(bottom = 4.dp),
            )

            Divider()

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                val precio = NumberFormat.getCurrencyInstance(Locale("US", "us")).format(itemModel.itemValor)

                Text(
                    text = "${itemModel.itemCosa}",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = "${itemModel.itemUn}: $precio",
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}







