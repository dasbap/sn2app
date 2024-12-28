package com.example.sn2app

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sn2app.ui.theme.Sn2appTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoScreen(navController: NavController) {
    Scaffold(
        topBar = {
            // Barre supérieure centrée avec titre
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Info",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp), // Ajoute un espace autour du contenu
                verticalArrangement = Arrangement.Center, // Centre verticalement
                horizontalAlignment = Alignment.CenterHorizontally // Centre horizontalement
            ) {
                // Titre de la page
                Text(
                    text = "Choose a person to view their details:",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                // Bouton pour Baptiste
                Button(
                    onClick = { navController.navigate("personPage/Baptiste") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp) // Espace entre les boutons
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
    )
}

@Preview(showBackground = true)
@Composable
fun InfoScreenPreview() {
    Sn2appTheme {
        InfoScreen(navController = rememberNavController())
    }
}