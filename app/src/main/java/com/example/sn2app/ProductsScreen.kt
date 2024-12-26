package com.example.sn2app

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
import com.example.sn2app.model.Product

@Composable
fun ProductsScreen(navController: NavController) {
    val categoriesState = remember { mutableStateListOf<Category>() }
    val productsState = remember { mutableStateListOf<Product>() }
    val isLoadingCategories = remember { mutableStateOf(true) }
    val isLoadingProducts = remember { mutableStateOf(false) }

    val productRepository = ProductRepository()

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
                        isLoadingProducts.value = true
                        productRepository.getProductsForCategory(category.productsUrl) {
                            productsState.clear()
                            productsState.addAll(it)
                            isLoadingProducts.value = false
                        }
                    }) {
                        Text(text = category.title)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (isLoadingProducts.value) {
                CircularProgressIndicator()
            } else if (productsState.isNotEmpty()) {
                LazyColumn {
                    items(productsState) { product ->
                        Text(text = product.title)
                    }
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
