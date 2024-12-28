package com.example.sn2app.ui

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
import androidx.compose.ui.unit.dp
import com.example.sn2app.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonPage(personName: String) {
    // Assurez-vous que les ressources d'images sont bien définies dans res/drawable
    val avatarRes = when (personName) {
        "Baptiste" -> R.drawable.avatar_baptiste
        "Gata" -> R.drawable.avatar_gata
        else -> R.drawable.avatar_default
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(personName, style = MaterialTheme.typography.titleMedium) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = avatarRes),
                contentDescription = "Avatar of $personName",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .border(2.dp, MaterialTheme.colorScheme.secondary, CircleShape)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "$personName@example.com",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            ClickableText(
                text = AnnotatedString("http://www.epsi.fr/"),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.primary
                ),
                onClick = { /* Lien décoratif, aucune action */ }
            )
        }
    }
}

