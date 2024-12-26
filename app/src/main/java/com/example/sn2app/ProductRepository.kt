import android.util.Log
import com.example.sn2app.model.Category
import com.example.sn2app.model.Product
import okhttp3.CacheControl
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class ProductRepository {

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()

    // Récupérer toutes les catégories
    fun getCategories(callback: (List<Category>) -> Unit) {
        val categoriesUrl = "https://api.jsonbin.io/v3/b/6760342bacd3cb34a8ba8657" // L'URL des catégories
        val request = Request.Builder().url(categoriesUrl).get().cacheControl(CacheControl.FORCE_NETWORK).build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("API Error", e.message.orEmpty())
                callback(emptyList())
            }

            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) {
                    Log.e("API Error", "Unexpected code: ${response.code}")
                    callback(emptyList())
                    return
                }

                val data = response.body?.string()
                val categories = mutableListOf<Category>()

                if (data != null) {
                    try {
                        val jsonResponse = JSONObject(data)
                        val categoriesArray = jsonResponse.getJSONArray("record")

                        for (i in 0 until categoriesArray.length()) {
                            val categoryObject = categoriesArray.getJSONObject(i)
                            val category = Category(
                                categoryObject.optString("categoryId", ""),
                                categoryObject.optString("title", ""),
                                categoryObject.optString("products_url", "")
                            )
                            categories.add(category)
                        }
                    } catch (e: Exception) {
                        Log.e("Parsing Error", "Error parsing categories data: ${e.message}")
                        callback(emptyList())
                        return
                    }
                }

                callback(categories)
            }
        })
    }

    // Récupérer les produits pour une catégorie
    fun fetchProducts(productsUrl: String, callback: (List<Product>) -> Unit) {
        val request = Request.Builder().url(productsUrl).get().cacheControl(CacheControl.FORCE_NETWORK).build()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("API Error", e.message.orEmpty())
                callback(emptyList())
            }

            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) {
                    Log.e("API Error", "Unexpected code: ${response.code}")
                    callback(emptyList())
                    return
                }

                val data = response.body?.string()
                val products = mutableListOf<Product>()

                if (data != null) {
                    try {
                        val jsonResponse = JSONObject(data)
                        val productArray = jsonResponse.getJSONArray("record")

                        for (i in 0 until productArray.length()) {
                            val productObject = productArray.getJSONObject(i)
                            val product = Product(
                                productObject.optString("name", ""),
                                productObject.optString("description", ""),
                                productObject.optString("pictureUrl", "")
                            )
                            products.add(product)
                        }
                    } catch (e: Exception) {
                        Log.e("Parsing Error", "Error parsing product data: ${e.message}")
                        callback(emptyList())
                        return
                    }
                }

                callback(products)
            }
        })
    }
}
