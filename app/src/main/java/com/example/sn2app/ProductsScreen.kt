import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.util.Locale

@Composable
fun ProductsScreen(navController: NavController) {
    var categories by remember { mutableStateOf<List<Locale.Category>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()

    // Lancer une coroutine pour récupérer les données de l'API
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            try {
                val response = RetrofitInstance.apiService.getCategories()
                if (response.isSuccessful) {
                    categories = response.body()?.record ?: emptyList()
                } else {
                    errorMessage = "Erreur serveur: ${response.code()}"
                }
            } catch (e: IOException) {
                errorMessage = "Erreur réseau: ${e.message}"
            } catch (e: HttpException) {
                errorMessage = "Erreur HTTP: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Produits", fontSize = 20.sp)

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            Text("Chargement...")
        } else if (errorMessage != null) {
            Text("Erreur: $errorMessage")
        } else {
            // Afficher les catégories sous forme de boutons
            categories.forEach { category ->
                Button(
                    onClick = {
                        // Naviguer vers l'URL des produits
                        navController.navigate("productList/${category.productsUrl}")
                    },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                ) {
                    Text(text = category.title, fontSize = 16.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductsScreenPreview() {
    ProductsScreen(navController = rememberNavController())
}
