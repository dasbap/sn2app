package com.example.sn2app

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage

@Composable
fun ProductDetailsScreen(
    navController: NavController,
    name: String?,
    description: String?,
    pictureUrl: String?
) {
    Text(
        text = name ?: "Nom inconnu",
        modifier = Modifier.padding(bottom = 8.dp)
    )
    Column(modifier = Modifier.padding(16.dp)) {
        AsyncImage(
            model = pictureUrl,
            contentDescription = null,
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Text(
            text = description ?: "Description indisponible",
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}
