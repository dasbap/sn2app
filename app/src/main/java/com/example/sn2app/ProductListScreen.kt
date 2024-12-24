package com.example.sn2app

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProductListScreen(productsUrl: String) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Displaying products from: $productsUrl")
    }
}

@Preview(showBackground = true)
@Composable
fun ProductListScreenPreview() {
    ProductListScreen(productsUrl = "https://api.jsonbin.io/v3/b/676033b0acd3cb34a8ba8614")
}
