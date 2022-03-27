package com.elitegadgetlabs.wingrid.composables

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Place
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.elitegadgetlabs.wingrid.ui.theme.appBackgroundColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun BottomNavBar(navController: NavController) {

    var selectedState = rememberSaveable { mutableStateOf("home_screen") }
    val coroutineScope = rememberCoroutineScope()

    fun navigate(dest: String) {
        coroutineScope.launch {
            delay(1000)
            navController.navigate(dest) {
                popUpTo = navController.graph.startDestinationId
                launchSingleTop = true
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        BottomAppBar(
            elevation = 12.dp,
            backgroundColor = MaterialTheme.colors.surface,
            modifier = Modifier.align(
                Alignment.BottomCenter
            )

        ) {
            BottomNavigationItem(
                icon = {
                    Icon(Icons.Outlined.Home, "Home")
                },
                selectedContentColor = appBackgroundColor,
                unselectedContentColor = Color.Black,
                onClick = {
                    Log.d("debug", "Home")
                    selectedState.value = "home_screen"
                    navigate("home_screen")
                },
                selected = selectedState.value == "home_screen"
            )

            BottomNavigationItem(
                icon = {
                    Icon(Icons.Outlined.Place, "Maps")
                },
                selectedContentColor = appBackgroundColor,
                unselectedContentColor = Color.Black,
                onClick = {
                    Log.d("debug", "Maps")
                    selectedState.value = "map_screen"
                    navigate("map_screen")
                },
                selected = selectedState.value == "map_screen"
            )

            BottomNavigationItem(
                icon = {
                    Icon(Icons.Filled.Person, "Profile")
                },
                selectedContentColor = appBackgroundColor,
                unselectedContentColor = Color.Black,
                onClick = {
                    Log.d("debug", "Profile")
                    selectedState.value = "info_screen"
                    navigate("info_screen")
                },
                selected = selectedState.value == "info_screen"
            )
        }
    }
}