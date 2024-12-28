package com.example.sn2app

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.sn2app.model.Product

@OptIn(ExperimentalMaterial3Api::class)
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

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(categoryName ?: "Produits", style = MaterialTheme.typography.titleLarge) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                if (isLoading.value) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(productsState) { product ->
                            ProductItem(product, navController)
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun ProductItem(product: Product, navController: NavController) {
    Card(
        onClick = {
            navController.navigate(
                "productDetails/${Uri.encode(product.name)}/${Uri.encode(product.description)}/${Uri.encode(product.pictureUrl)}"
            )
        },
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Image du produit
            AsyncImage(
                model = product.pictureUrl,
                contentDescription = product.name,
                modifier = Modifier
                    .size(80.dp)
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .padding(4.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // DÃ©tails du produit
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = product.name ?: "Nom indisponible",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = product.description ?: "Description non disponible",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCategoryProductsScreen() {
    CategoryProductsScreen(
        navController = rememberNavController(),
        categoryName = "Boissons",
        categoryUrl = "67603385ad19ca34f8dc09db"
    )
}