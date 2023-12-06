package com.dflch.water.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dflch.water.navigation.AppScreens

@Composable
fun MainScreen(navController: NavController) {
    BodyContentMain(navController = navController)
}


@Preview
@Composable
fun Profile() {
    Column (
        Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = Color(android.graphics.Color.parseColor("#ECECEC"))),
            horizontalAlignment = Alignment.CenterHorizontally
    ){


    }

}


@Composable
fun BodyContentMain(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("MAIN SCREEN")
        Spacer(modifier = Modifier.width(8.dp))
        Button(onClick = {
            navController.navigate(route = AppScreens.TasksScreen.route)
        }) {
            Text("ADD TASK")
        }
    }
}

