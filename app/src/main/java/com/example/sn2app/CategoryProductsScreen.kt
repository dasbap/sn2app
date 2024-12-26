import android.util.Log
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
import com.example.sn2app.model.Product

@Composable
fun CategoryProductsScreen(navController: NavController, categoryName: String?) {
    // L'URL pour récupérer les produits
    val productUrl = "https://api.jsonbin.io/v3/b/$categoryName"

    // État pour stocker les produits et l'état de chargement
    val productsState = remember { mutableStateListOf<Product>() }
    val isLoading = remember { mutableStateOf(true) }

    // Utiliser LaunchedEffect pour charger les produits
    LaunchedEffect(Unit) {
        if (categoryName != null) {
            ProductRepository().fetchProducts(productUrl) { fetchedProducts ->
                productsState.clear() // Nettoyer la liste avant d'ajouter les nouveaux produits
                productsState.addAll(fetchedProducts)
                isLoading.value = false // Changer l'état de chargement une fois que les données sont récupérées
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
            // Afficher la liste des produits avec LazyColumn
            LazyColumn {
                items(productsState) { product ->
                    ProductItem(product)
                }
            }
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = product.name, modifier = Modifier.padding(bottom = 4.dp))
        Text(text = product.description, modifier = Modifier.padding(bottom = 4.dp))
        Text(text = product.pictureUrl, modifier = Modifier.padding(bottom = 4.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCategoryProductsScreen() {
    CategoryProductsScreen(
        navController = rememberNavController(),
        categoryName = "67603385ad19ca34f8dc09db"
    )
}
