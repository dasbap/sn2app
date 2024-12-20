package com.example.sn2app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.*

import com.example.sn2app.ui.theme.Sn2appTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Sn2appTheme {
                val navController = rememberNavController()
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
                            InfoScreen()
                        }
                        composable("products") {
                            ProductsScreen()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Button(
            onClick = { navController.navigate("info") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            Text("Go to Info")
        }
        Button(
            onClick = { navController.navigate("products") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Go to Products")
        }
    }
}

@Composable
fun InfoScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Info Page")
    }
}

@Composable
fun ProductsScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Products Page")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Sn2appTheme {
        HomeScreen(navController = rememberNavController())
    }
}
