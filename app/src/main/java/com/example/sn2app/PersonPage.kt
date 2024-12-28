@file:Suppress("DEPRECATION")

package com.example.sn2app

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonPage(personName: String) {
    // Détermine l'avatar en fonction du nom de la personne
    val avatarRes = when (personName) {
        "Baptiste" -> R.drawable.avatar_baptiste
        "Gata" -> R.drawable.avatar_gata
        else -> R.drawable.avatar_default
    }

    Scaffold(
        topBar = {
            // Barre supérieure avec titre centré
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = personName,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Affiche l'avatar
                Image(
                    painter = painterResource(id = avatarRes),
                    contentDescription = "Avatar of $personName",
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .border(4.dp, MaterialTheme.colorScheme.secondary, CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Adresse email
                Text(
                    text = "$personName@example.com",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.height(16.dp))

                ClickableText(
                    text = AnnotatedString("http://www.epsi.fr/"),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.primary
                    ),
                    onClick = { /* Lien sans action */ }
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PersonPagePreview() {
    PersonPage(personName = "Gata")
}