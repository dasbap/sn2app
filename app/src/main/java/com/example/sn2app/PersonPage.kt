package com.example.sn2app

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PersonPage(personName: String?) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("This is the page for $personName.")

        Spacer(modifier = Modifier.height(16.dp))

        when (personName) {
            "Baptiste" -> {
                Text("More details about Baptiste can go here.")
            }
            "Gata" -> {
                Text("More details about Gata can go here.")
            }
            else -> {
                Text("Details not available.")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PersonPagePreview() {
    PersonPage(personName = "Baptiste")
}
