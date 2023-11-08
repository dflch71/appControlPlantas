package com.dflch.water.screens.drawer.items

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dflch.water.caItems.ui.model.ItemModel
import com.dflch.water.caItems.ui.viewmodel.ItemViewModel
import java.text.NumberFormat
import java.util.Locale

@Composable
fun ItemsScreen(
    itemViewModel: ItemViewModel
) {

    val state by itemViewModel.stateItem.collectAsState()

    //itemViewModel.getAllItemDB()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        SearchBarItem()

        if (state.items.isNotEmpty()) {

            var selectedIndex by remember { mutableStateOf(-1) }
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {

                itemsIndexed(items = state.items) { index, item ->
                    CardItem(item, index, selectedIndex) { i ->
                        selectedIndex = i
                    }
                }
            }

        } else {
            Text(
                text = "No hay items ${state.status}",
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarItem(){

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
                    onClick = {
                    onSearch(query) },
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
                            contentDescription = "Close")
                    }
                }
            }
        }

        /*
        trailingIcon = {
            if (active) {
                Icon(
                    modifier = Modifier.clickable {
                        if (query.isNotEmpty()) {
                            query = ""
                        } else {
                            active = false
                        }
                    },
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close icon"
                )
            }
        }
        trailingIcon = {
            IconButton(
                onClick = {
                    onSearch(query) },
                    enabled = query.isNotEmpty()
            ){
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = ""
                )
            }
        }*/

    ) {

        if (query.isNotEmpty()) {
            val filteredContries = countries.filter { it.second.contains(query, true) }

            LazyColumn {
                items(filteredContries) { (flag, name) ->
                    Text(
                        text = "$flag $name",
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable {
                                Toast
                                    .makeText(ctx, name, Toast.LENGTH_SHORT)
                                    .show()
                                active = false
                            }
                    )
                }
            }
        }
    }
}

@Composable
fun CardItem(itemModel: ItemModel, index: Int, selectedIndex: Int, onClick: (Int) -> Unit) {

    val backgroundColor = if (index == selectedIndex) MaterialTheme.colorScheme.inversePrimary else MaterialTheme.colorScheme.background

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onClick(index) }
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

val countries = listOf(
    "1" to "Afganistan",
    "2" to "Albania",
    "3" to "Algeria",
    "4" to "Colombia",
    "5" to "USA",
    "6" to "UK"
)



