package com.example.sn2app


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sn2app.model.Category

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsScreen(navController: NavController) {
    val categoriesState = remember { mutableStateListOf<Category>() }
    val isLoadingCategories = remember { mutableStateOf(true) }
    val productRepository = ProductRepository()

    // Chargement des catÃ©gories depuis l'API
    LaunchedEffect(Unit) {
        productRepository.getCategories { categories ->
            categoriesState.clear()
            categoriesState.addAll(categories)
            isLoadingCategories.value = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Products", style = MaterialTheme.typography.titleLarge) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                if (isLoadingCategories.value) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        items(categoriesState) { category ->
                            CategoryCard(category, onClick = {
                                val url = category.productsUrl.replace("https://api.jsonbin.io/v3/b/", "")
                                navController.navigate("categoryProducts/${category.title}/$url")
                            })
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun CategoryCard(category: Category, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = category.title,
                style = MaterialTheme.typography.titleMedium.copy(fontSize = 20.sp),
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Explore the best products in ${category.title}.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProductsScreen() {
    ProductsScreen(navController = rememberNavController())
}