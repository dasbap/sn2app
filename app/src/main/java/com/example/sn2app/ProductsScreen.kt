package com.example.sn2app

import ProductRepository
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sn2app.model.Category

@Composable
fun ProductsScreen(navController: NavController) {
    val categoriesState = remember { mutableStateListOf<Category>() }
    val isLoadingCategories = remember { mutableStateOf(true) }
    val productRepository = ProductRepository()

    // Récupérer les catégories depuis l'API
    LaunchedEffect(Unit) {
        productRepository.getCategories { categories ->
            categoriesState.clear()
            categoriesState.addAll(categories)
            isLoadingCategories.value = false
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        if (isLoadingCategories.value) {
            CircularProgressIndicator()
        } else {
            LazyColumn {
                items(categoriesState) { category ->
                    Button(onClick = {
                        var url = category.productsUrl
                        url = url.replace("https://api.jsonbin.io/v3/b/", "")
                        navController.navigate("categoryProducts/$url")
                    }) {
                        Text(text = category.title)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProductsScreen() {
    ProductsScreen(navController = rememberNavController())
}
