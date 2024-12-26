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
    val productUrl = "https://api.jsonbin.io/v3/b/$categoryName"

    val productsState = remember { mutableStateListOf<Product>() }
    val isLoading = remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        if (categoryName != null) {
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
                    ProductItem(product)
                }
            }
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = product.name.toString(), modifier = Modifier.padding(bottom = 4.dp))
        Text(text = product.description.toString(), modifier = Modifier.padding(bottom = 4.dp))
        Text(text = product.pictureUrl.toString(), modifier = Modifier.padding(bottom = 4.dp))
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
