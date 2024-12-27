package com.example.sn2app

import ProductRepository
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.sn2app.model.Product

@Composable
fun CategoryProductsScreen(navController: NavController, categoryName: String?, categoryUrl: String?) {
    val productUrl = "https://api.jsonbin.io/v3/b/$categoryUrl"
    val productsState = remember { mutableStateListOf<Product>() }
    val isLoading = remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        if (categoryUrl != null) {
            ProductRepository().fetchProducts(productUrl) { fetchedProducts ->
                productsState.clear()
                productsState.addAll(fetchedProducts)
                isLoading.value = false
            }
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Produits de la catégorie : $categoryName", modifier = Modifier.padding(bottom = 16.dp))

        if (isLoading.value) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn {
                items(productsState) { product ->
                    ProductItem(product, navController)
                }
            }
        }
    }
}

@Composable
fun ProductItem(product: Product, navController: NavController) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                navController.navigate(
                    "productDetails/${Uri.encode(product.name)}/${Uri.encode(product.description)}/${Uri.encode(product.pictureUrl)}"
                )
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = product.pictureUrl,
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .padding(end = 8.dp)
        )
        Text(
            text = product.name.toString(),
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCategoryProductsScreen() {
    CategoryProductsScreen(
        navController = rememberNavController(),
        categoryName = "voldemort",
        categoryUrl = "67603385ad19ca34f8dc09db"
    )
}
