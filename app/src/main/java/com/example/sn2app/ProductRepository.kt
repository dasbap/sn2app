package com.example.sn2app

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

    // Récupérer les catégories depuis l'API
    fun getCategories(callback: (List<Category>) -> Unit) {
        val categoriesUrl = "https://api.jsonbin.io/v3/b/6760342bacd3cb34a8ba8657"
        val request = Request.Builder().url(categoriesUrl).get().cacheControl(CacheControl.FORCE_NETWORK)
            .build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("API Error", e.message.orEmpty())
            }

            override fun onResponse(call: Call, response: Response) {
                val data = response.body?.string()
                val categories = mutableListOf<Category>()

                if (data != null) {
                    val jsonResponse = JSONObject(data)
                    val categoriesArray = jsonResponse.getJSONArray("record")

                    for (i in 0 until categoriesArray.length()) {
                        val categoryObject = categoriesArray.getJSONObject(i)
                        val category = Category(
                            categoryObject.optString("category_id", ""),
                            categoryObject.optString("title", ""),
                            categoryObject.optString("products_url", "")
                        )
                        categories.add(category)
                    }
                }

                callback(categories)
            }
        })
    }

    // Récupérer les produits pour une catégorie
    fun getProductsForCategory(productsUrl: String, callback: (List<Product>) -> Unit) {
        val request = Request.Builder().url(productsUrl).get().cacheControl(CacheControl.FORCE_NETWORK)
            .build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("API Error", e.message.orEmpty())
            }

            override fun onResponse(call: Call, response: Response) {
                val data = response.body?.string()
                val products = mutableListOf<Product>()

                if (data != null) {
                    val jsonResponse = JSONObject(data)
                    val productsArray = jsonResponse.getJSONArray("record")

                    for (i in 0 until productsArray.length()) {
                        val productObject = productsArray.getJSONObject(i)
                        val product = Product(
                            productObject.optString("title", "Unknown Product")
                        )
                        products.add(product)
                    }
                }

                callback(products)
            }
        })
    }
}
