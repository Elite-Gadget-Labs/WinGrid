package com.elitegadgetlabs.wingrid.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun InfoScreen(navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item { Spacer(modifier = Modifier.height(20.dp)) } //vertical spacer

        item {
            Text(
                text = "Info",
                style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.ExtraBold),
                modifier = Modifier.padding(top = 8.dp),
                textAlign = TextAlign.Center
            )
        }

        item { Spacer(modifier = Modifier.height(20.dp)) } //vertical spacer

        item {
            Text(
                text = "Types of Chargers:",
                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.ExtraBold),
                modifier = Modifier.padding(top = 8.dp),
                textAlign = TextAlign.Center
            )
        }
        item {
            Text(
                text = "LVL 1 - 8km/hr of charging",
                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.ExtraBold),
                modifier = Modifier.padding(top = 8.dp),
                textAlign = TextAlign.Center
            )
        }
        item {
            Text(
                text = "LVL 2 - 25km/hr of charging",
                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.ExtraBold),
                modifier = Modifier.padding(top = 8.dp),
                textAlign = TextAlign.Center
            )
        }
        item {
            Text(
                text = "LVL 3 - 250km/hr of charging",
                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.ExtraBold),
                modifier = Modifier.padding(top = 8.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}