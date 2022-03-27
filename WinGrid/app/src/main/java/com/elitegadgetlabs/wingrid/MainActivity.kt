package com.elitegadgetlabs.wingrid

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Place
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.elitegadgetlabs.wingrid.composables.*
import com.elitegadgetlabs.wingrid.ui.theme.WinGridTheme
import com.elitegadgetlabs.wingrid.ui.theme.appBackgroundColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WinGridTheme {
                ShowHomeScreen()
            }
        }
    }
    @Composable
    fun ShowHomeScreen() {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "home_screen", builder = {
            composable("home_screen", content = { HomeScreen(navController = navController) })
            composable("map_screen", content = { MapScreen(navController = navController, mainViewModel = mainViewModel) })
            composable("info_screen", content = { InfoScreen(navController = navController) })
        })

        BottomNavBar(navController)
    }
}


