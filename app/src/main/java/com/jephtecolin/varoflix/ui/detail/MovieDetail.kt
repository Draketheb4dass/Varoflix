package com.jephtecolin.varoflix.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun MovieDetail(
    movieId: Long,
    viewModel: MovieDetailViewModel,
    onBackPressed: () -> Unit
) {
    Column() {
        Text(text = "Detail Screen not yet implemented")
    }
}