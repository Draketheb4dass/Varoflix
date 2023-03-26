package com.jephtecolin.varoflix.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.jephtecolin.varoflix.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    onProfileClick: () -> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        title = {
            Text(
                text = stringResource(id = R.string.home),
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )
        },
        actions = {
            IconButton(onClick = onProfileClick) {
                Icon(Icons.Filled.Favorite, contentDescription = null, tint = Color.White)
            }
        },
    )
}