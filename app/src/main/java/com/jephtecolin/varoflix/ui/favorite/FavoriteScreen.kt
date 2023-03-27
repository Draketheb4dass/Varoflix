package com.jephtecolin.varoflix.ui.favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jephtecolin.varoflix.ui.components.MovieCard
import com.jephtecolin.varoflix.ui.components.TopBarFavorite

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel,
    onBackPressed: () -> Unit
) {

    val favoriteMovies by viewModel.favoriteMovies.collectAsState()

    Scaffold(topBar = { TopBarFavorite(onBackPressed = onBackPressed) }) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding()),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .statusBarsPadding()
                        .padding(horizontal = 24.dp),
                    content = {
                        items(favoriteMovies) { favoriteMovie ->
                            MovieCard(movie = favoriteMovie, onClickCard = {})
                        }
                    }
                )
            }
        }
    }

}