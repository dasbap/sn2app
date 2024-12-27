package com.example.sn2app

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
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument

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
                        composable("categoryProducts/{categoryProduct}/{productUrl}") { backStackEntry ->
                            val productUrl = backStackEntry.arguments?.getString("productUrl")
                            val categoryProduct = backStackEntry.arguments?.getString("categoryProduct")
                            CategoryProductsScreen(navController = navController,categoryName = categoryProduct, categoryUrl = productUrl)

                        }
                        composable(
                            route = "productDetails/{name}/{description}/{pictureUrl}",
                            arguments = listOf(
                                navArgument("name") { type = NavType.StringType },
                                navArgument("description") { type = NavType.StringType },
                                navArgument("pictureUrl") { type = NavType.StringType }
                            )
                        ) { backStackEntry ->
                            val name = backStackEntry.arguments?.getString("name")
                            val description = backStackEntry.arguments?.getString("description")
                            val pictureUrl = backStackEntry.arguments?.getString("pictureUrl")

                            ProductDetailsScreen(navController, name, description, pictureUrl)
                        }


                    }
                }
            }
        }
    }
}
