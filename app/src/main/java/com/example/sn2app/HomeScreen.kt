package com.example.sn2app

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sn2app.ui.SplashScreen
import com.example.sn2app.ui.theme.Sn2appTheme

// Écran d'accueil
@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Titre de l'écran d'accueil
        Text(
            text = "Welcome to the Home Page!",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Bouton pour accéder à la page Info
        Button(
            onClick = { navController.navigate("info") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Text("Go to Info Page")
        }

        // Bouton pour accéder à la page des produits
        Button(
            onClick = { navController.navigate("products") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Go to Products Page")
        }
    }
}

// Prévisualisation de l'écran d'accueil
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    Sn2appTheme {
        HomeScreen(navController = rememberNavController())
    }
}

// Activité de l'écran Splash
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Sn2appTheme {
                SplashScreen()
            }
        }

        // Délai de 2 secondes avant de passer à MainActivity
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish() // Ferme l'activité Splash après la transition
        }, 2000)
    }
}