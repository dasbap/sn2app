package com.example.sn2app

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun InfoScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Info Page")

        Spacer(modifier = Modifier.height(16.dp))

        // Bouton pour aller à la page de Baptiste
        Button(
            onClick = { navController.navigate("personPage/Baptiste") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            Text("Go to Baptiste's Page")
        }

        // Bouton pour aller à la page de Gata
        Button(
            onClick = { navController.navigate("personPage/Gata") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Go to Gata's Page")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InfoScreenPreview() {
    InfoScreen(navController = rememberNavController())
}
