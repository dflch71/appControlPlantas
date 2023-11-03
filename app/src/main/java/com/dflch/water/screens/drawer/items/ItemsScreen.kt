package com.dflch.water.screens.drawer.items

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FindInPage
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CheckboxDefaults.colors
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dflch.water.caItems.ui.model.ItemModel
import com.dflch.water.caItems.ui.viewmodel.ItemViewModel
import com.dflch.water.utils.Constants.floatFormatDecimal
import java.text.NumberFormat
import java.util.Locale

@Composable
fun SettingsScreen(
    itemViewModel: ItemViewModel
) {

    val state by itemViewModel.stateItem.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBarItem()

        Divider(
            Modifier
                .background(Color(0xFFF9F9F9))
                .height(4.dp)
                .weight(1f)
        )

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
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = ""
                    )
                }
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    onSearch(query) },
                    enabled = query.isNotEmpty()
            ){
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = ""
                )
            }
        }
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
            //.size(width = 240.dp, height = 100.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onClick(index) }
            .padding(8.dp),

        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
    ) {

        Column {

            Text(
                text = "${itemModel.itemDesc}",
                modifier = Modifier.padding(4.dp),
                fontSize = 17.sp,
                textAlign = TextAlign.Start,
                maxLines = 3
            )

            Divider(modifier = Modifier.padding(4.dp))

            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                val precio = NumberFormat.getCurrencyInstance(Locale("US", "us")).format(itemModel.itemValor)

                Text(
                    text = "${itemModel.itemCosa}",
                    fontSize = 17.sp, fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 10.dp),
                    color = MaterialTheme.colorScheme.inversePrimary
                )

                Text(
                    text = "${itemModel.itemUn}: $precio",
                    fontSize = 17.sp, fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(end = 10.dp)
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



