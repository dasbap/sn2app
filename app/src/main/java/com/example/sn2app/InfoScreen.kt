package com.example.sn2app

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoScreen(navController: NavController) {
    Scaffold(
        topBar = {
            // Header avec "Info"
            CenterAlignedTopAppBar(
                title = { Text("Info", fontSize = 20.sp) }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp), // Remplissage autour du contenu
            horizontalAlignment = Alignment.CenterHorizontally // Centre les éléments horizontalement
        ) {
            Text(
                "Choose a person to view their details:",
                modifier = Modifier.padding(bottom = 16.dp),
                fontSize = 18.sp
            )

            // Bouton pour Baptiste
            Button(
                onClick = { navController.navigate("personPage/Baptiste") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp) // Espacement entre les boutons
            ) {
                Text("Go to Baptiste's Page")
            }

            // Bouton pour Gata
            Button(
                onClick = { navController.navigate("personPage/Gata") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Go to Gata's Page")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InfoScreenPreview() {
    InfoScreen(navController = rememberNavController())
}