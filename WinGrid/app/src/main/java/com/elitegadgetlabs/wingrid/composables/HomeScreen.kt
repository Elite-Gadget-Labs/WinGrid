package com.elitegadgetlabs.wingrid.composables

import android.util.Log
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Place
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.elitegadgetlabs.wingrid.R
import com.elitegadgetlabs.wingrid.ui.theme.appBackgroundColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController, mainViewModel: MainViewModel = MainViewModel()) {
    //TextField variables
    var Chargers by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("")) }

    /*
    Creating MutableInteractionSources for the TextField.
    This allows listening and responding to interaction changes inside these components.
     */
    val Chargers_InteractionState = remember { MutableInteractionSource() }

    /*
    A Scaffold is a layout which implements the basic material design layout structure.
    You can add things like a TopBar, BottomBar, FAB or a Drawer.
    */
    Scaffold {
        //The LazyColumn is a vertically scrolling list that only composes and lays out its currently visible items.
        LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
                ) {
                item { Spacer(modifier = Modifier.height(20.dp)) } //vertical spacer

                item {
                    Text(
                        text = "WinGRID",
                        style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.ExtraBold),
                        modifier = Modifier.padding(top = 8.dp),
                        textAlign = TextAlign.Center
                    )
                }

                item { Spacer(modifier = Modifier.height(20.dp)) } //vertical spacer

                item {
                    Text(
                        text = "Currently installed chargers:",
                        style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.ExtraBold),
                        modifier = Modifier.padding(top = 8.dp),
                        textAlign = TextAlign.Center
                    )
            }

            item{
                Row(modifier = Modifier.padding(vertical = 8.dp)){

                    Text(text = "0")
                }
            }


            item { Spacer(modifier = Modifier.height(20.dp)) } //vertical spacer

            item {
                Text(
                    text = "Enter the number of new chargers to be installed",
                    style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.ExtraBold),
                    modifier = Modifier.padding(top = 8.dp),
                    textAlign = TextAlign.Center
                )
            }

            item {
                OutlinedTextField(
                    value = Chargers,
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    label = { Text(text = "number of new chargers") },
                    onValueChange = {
                        Chargers = it
                    },
                    interactionSource = Chargers_InteractionState,
                )
            }


            item {
                var loading by remember { mutableStateOf(false) }
                Button(
                    onClick = {
                        loading = true
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .height(50.dp)
                        .clip(CircleShape)
                ) {
                    if (loading) {
                        HorizontalDottedProgressBar()

                        LaunchedEffect(true) {
                            delay(1000)
                            mainViewModel.numNewChargers = Chargers.text.toInt()
                            navController.navigate("map_screen") {
                                popUpTo = navController.graph.startDestinationId
                                launchSingleTop = true
                            }
                            //loading = false
                        }

                    } else {
                        Text(text = "Generate Locations")
                    }
                }
            }

        }



    }
}
