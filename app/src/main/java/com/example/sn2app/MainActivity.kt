package com.example.sn2app

import CategoryProductsScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.WindowInsetsCompat
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*

import com.example.sn2app.ui.theme.Sn2appTheme


@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.hide(WindowInsetsCompat.Type.systemBars())

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
                            InfoScreen(navController)
                        }
                        composable("personPage/{personName}") { backStackEntry ->
                            PersonPage(personName = backStackEntry.arguments?.getString("personName"))
                        }
                        composable("products") {
                            ProductsScreen(navController)
                        }
                        composable("categoryProducts/{productUrl}") { backStackEntry ->
                            val productUrl = backStackEntry.arguments?.getString("productUrl")
                            CategoryProductsScreen(navController = navController, categoryName = productUrl)

                        }
                    }
                }
            }
        }
    }
}
