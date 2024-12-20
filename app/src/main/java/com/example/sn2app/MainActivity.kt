package com.example.sn2app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.*

import com.example.sn2app.ui.theme.Sn2appTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Sn2appTheme {
                // Créer un NavController
                val navController = rememberNavController()

                // Créer la structure de navigation
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("home") {
                            HomeScreen(navController)
                        }
                        composable("info") {
                            InfoScreen(navController)
                        }
                        composable("personPage/{personName}") { backStackEntry ->
                            backStackEntry.arguments?.getString("personName")
                                ?.let { PersonPage(personName = it) }
                        }
                        composable("products") {
                            ProductsScreen(navController)
                        }
                    }
                }
            }
        }
    }
}
